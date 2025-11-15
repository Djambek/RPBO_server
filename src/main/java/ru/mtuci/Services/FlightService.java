package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Flight;
import ru.mtuci.Models.FlightRequest;
import ru.mtuci.Repositories.FlightRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FlightService {
    public final FlightRepository flightRepository;

    public ResponseEntity<Flight> findById(UUID id){
        if (!flightRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightRepository.findById(id).get());
    }

    public ResponseEntity<List<Flight>> findAll(){
        return ResponseEntity.ok(flightRepository.findAll());
    }

    public ResponseEntity<Flight> create(FlightRequest flightRequest) {
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

    public ResponseEntity<Flight> update(UUID id, FlightRequest flightRequest) {
        if (!flightRepository.existsById(id)){
            return ResponseEntity.notFound().build();
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
}
