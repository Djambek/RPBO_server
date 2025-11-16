package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Airport;
import ru.mtuci.Models.AirportRequest;
import ru.mtuci.Repositories.AirportRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public ResponseEntity<Airport> findById(UUID id){
        if (!airportRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airportRepository.findById(id).get());
    }

    public ResponseEntity<List<Airport>> findAll(){
        return ResponseEntity.ok(airportRepository.findAll());
    }

    public ResponseEntity<Airport> create(AirportRequest airportRequest){
        Airport airport = new Airport(
                airportRequest.getCode(),
                airportRequest.getName(),
                airportRequest.getCity()

        );
        return ResponseEntity.ok(airportRepository.save(airport));
    }

    public ResponseEntity<Airport> update(UUID id, AirportRequest airportRequest) {
        if (!airportRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Airport airport = airportRepository.findById(id).get();
        airport.setCity(airportRequest.getCity());
        airport.setCode(airportRequest.getCode());
        airport.setName(airportRequest.getName());

        return ResponseEntity.ok(airportRepository.save(airport));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!airportRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        airportRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public boolean existsById(UUID airportId) {
        return airportRepository.existsById(airportId);
    }

    public void clean() {
        airportRepository.deleteAll();
    }
}
