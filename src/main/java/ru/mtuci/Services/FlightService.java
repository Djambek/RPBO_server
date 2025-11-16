package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Flight;
import ru.mtuci.Models.FlightRequest;
import ru.mtuci.Repositories.FlightRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FlightService {
    public final FlightRepository flightRepository;

    public final AirportService airportService;
    public final AircraftService aircraftService;

    public ResponseEntity<Flight> findById(UUID id){
        if (!flightRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightRepository.findById(id).get());
    }

    public ResponseEntity<List<Flight>> findAll(){
        return ResponseEntity.ok(flightRepository.findAll());
    }

    public ResponseEntity<?> create(FlightRequest flightRequest) {
        if (!aircraftService.existsById(flightRequest.getAircraftId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found aircraft with id: " + flightRequest.getAircraftId()));
        }
        if (!airportService.existsById(flightRequest.getArrivalAirportId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found airport with id: " + flightRequest.getArrivalAirportId()));
        }
        if (!airportService.existsById(flightRequest.getDepartureAirportId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found airport with id: " + flightRequest.getDepartureAirportId()));
        }
        Flight flight = new Flight(
                flightRequest.getFlightNumber(),
                flightRequest.getDepartureAirportId(),
                flightRequest.getArrivalAirportId(),
                flightRequest.getDepartureTime(),
                flightRequest.getArrivalTime(),
                flightRequest.getAircraftId()
        );

        return ResponseEntity.ok(flightRepository.save(flight));
    }

    public ResponseEntity<?> update(UUID id, FlightRequest flightRequest) {
        if (!flightRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        if (!aircraftService.existsById(flightRequest.getAircraftId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found aircraft with id: " + flightRequest.getAircraftId()));
        }
        if (!airportService.existsById(flightRequest.getArrivalAirportId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found airport with id: " + flightRequest.getArrivalAirportId()));
        }
        if (!airportService.existsById(flightRequest.getDepartureAirportId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",
                    "Not found airport with id: " + flightRequest.getDepartureAirportId()));
        }

        Flight flight = flightRepository.findById(id).get();
        flight.setFlightNumber(flightRequest.getFlightNumber());
        flight.setAircraftId(flightRequest.getAircraftId());
        flight.setArrivalTime(flightRequest.getArrivalTime());
        flight.setDepartureTime(flightRequest.getDepartureTime());
        flight.setArrivalAirportId(flightRequest.getArrivalAirportId());
        flight.setDepartureAirportId(flightRequest.getDepartureAirportId());

        return ResponseEntity.ok(flightRepository.save(flight));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!flightRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        flightRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public Flight findByNumber(String number){
        return flightRepository.findByFlightNumber(number);
    }

    public List<Flight> findUncompletedFlight(LocalDateTime time){
        return flightRepository.findUncompletedFlight(time);
    }

    public boolean existsById(UUID flightId) {
        return flightRepository.existsById(flightId);
    }

    public void clean() {
        flightRepository.deleteAll();
    }
}
