package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.Entities.Booking;
import ru.mtuci.Entities.Flight;
import ru.mtuci.Entities.Passenger;
import ru.mtuci.Models.BlackListRequest;
import ru.mtuci.Models.BookingRequest;
import ru.mtuci.Models.EndFlightRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogicService {
    private final PassengerService passengerService;
    private final BookingService bookingService;
    private final FlightService flightService;
    private final AircraftService aircraftService;

    @Transactional
    public ResponseEntity<Map<String, String>> toBlackList(BlackListRequest blackListRequest){
        ResponseEntity<Void> statusDelete = passengerService.delete(blackListRequest.getPassengerId());
        if (statusDelete.getStatusCode() == HttpStatus.NO_CONTENT){
            bookingService.deleteAllBookingByPassengerId(blackListRequest.getPassengerId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",
                "Not found passenger with id: " + blackListRequest.getPassengerId()));
    }

    @Transactional
    public ResponseEntity<Map<String, String>> endFlightByNumber(EndFlightRequest endFlightRequest){
        Flight flight = flightService.findByNumber(endFlightRequest.getFlightNumber());
        if (flight == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",
                    "Not found flight with number: " + endFlightRequest.getFlightNumber()));
        }
        bookingService.deleteAllByFlightId(flight.getId());
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public ResponseEntity<Map<String, Integer>> getNowFlying(LocalDateTime time){
        List<Flight> flights = flightService.findUncompletedFlight(time);
        Integer summ = 0;
        for (Flight flight: flights){
            summ += bookingService.countByFlightId(flight.getId());
        }
        return ResponseEntity.ok(Map.of("count", summ));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<?> register(BookingRequest bookingRequest) {
        if (!flightService.existsById(bookingRequest.getFlightId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found flight with id: " + bookingRequest.getFlightId()));
        }
        if (!passengerService.existsById(bookingRequest.getPassengerId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found passenger with id: " + bookingRequest.getPassengerId()));
        }
        if (bookingService.alreadyRegistered(bookingRequest.getFlightId(), bookingRequest.getPassengerId())){
            return ResponseEntity.ok(Map.of("status", "Already registered"));
        }
        if (bookingService.seatNumberAlreadyBooked(bookingRequest.getFlightId(), bookingRequest.getSeatNumber())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status",
                    "This seat is already booked"));
        }
        Flight flight = flightService.findById(bookingRequest.getFlightId()).getBody();
        int capacity = aircraftService.findById(flight.getAircraftId()).getBody().getCapacity();
        if (capacity == bookingService.countByFlightId(flight.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "All seats in this flight is booked"));
        }
        return bookingService.create(bookingRequest);
    }

    public ResponseEntity<List<String>> getEmailByFlightId(UUID id){
        if (!flightService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        List<Booking> bookings = bookingService.getBookingsByFlightId(id);

        ArrayList<String> emails = new ArrayList<>();
        for (Booking booking: bookings){
            Passenger passenger = passengerService.findById(booking.getPassengerId()).getBody();
            emails.add(passenger.getEmail());
        }
        return ResponseEntity.ok(emails);
    }

}
