package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Booking;
import ru.mtuci.Models.BookingRequest;
import ru.mtuci.Repositories.BookingRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    private final FlightService flightService;
    private final PassengerService passengerService;

    public ResponseEntity<Booking> findById(UUID id){
        if (!bookingRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingRepository.findById(id).get());
    }

    public ResponseEntity<List<Booking>> findAll(){
        return ResponseEntity.ok((bookingRepository.findAll()));
    }

    public ResponseEntity<?> create(BookingRequest bookingRequest){
        if (!flightService.existsById(bookingRequest.getFlightId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found flight with id: " + bookingRequest.getFlightId()));
        }
        if (!passengerService.existsById(bookingRequest.getPassengerId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found passenger with id: " + bookingRequest.getPassengerId()));
        }
        Booking booking = new Booking(
                bookingRequest.getFlightId(),
                bookingRequest.getPassengerId(),
                bookingRequest.getSeatNumber()
        );
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    public ResponseEntity<?> update(UUID id, BookingRequest bookingRequest){
        if (!bookingRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        if (!flightService.existsById(bookingRequest.getFlightId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found flight with id: " + bookingRequest.getFlightId()));
        }
        if (!passengerService.existsById(bookingRequest.getPassengerId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found passenger with id: " + bookingRequest.getPassengerId()));
        }
        Booking booking = bookingRepository.findById(id).get();
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setPassengerId(bookingRequest.getPassengerId());
        booking.setSeatNumber(bookingRequest.getSeatNumber());

        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!bookingRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void deleteAllBookingByPassengerId(UUID id) {
        bookingRepository.deleteAllByPassengerId(id);
    }

    public void deleteAllByFlightId(UUID id){
        bookingRepository.deleteAllByFlightId(id);
    }

    public Integer countByFlightId(UUID id) {
        return bookingRepository.countByFlightId(id);
    }

    public boolean alreadyRegistered(UUID flightId, UUID passengerId) {
        return bookingRepository.existsByFlightIdAndPassengerId(flightId, passengerId);
    }

    public boolean seatNumberAlreadyBooked(UUID flightId, String seatNumber) {
        return bookingRepository.existsByFlightIdAndSeatNumber(flightId, seatNumber);
    }

    public List<Booking> getBookingsByFlightId(UUID id) {
        return bookingRepository.findByFlightId(id);
    }

    public void clean() {
        bookingRepository.deleteAll();
    }
}
