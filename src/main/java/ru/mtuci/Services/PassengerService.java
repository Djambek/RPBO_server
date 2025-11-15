package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Passenger;
import ru.mtuci.Models.PassengerRequest;
import ru.mtuci.Repositories.PassengerRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public ResponseEntity<Passenger> findById(UUID id){
        if (!passengerRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passengerRepository.findById(id).get());
    }

    public ResponseEntity<List<Passenger>> findAll(){
        return ResponseEntity.ok(passengerRepository.findAll());
    }

    public ResponseEntity<Passenger> create(PassengerRequest passengerRequest) {
        Passenger passenger = new Passenger(
                passengerRequest.getFirstName(),
                passengerRequest.getLastName(),
                passengerRequest.getEmail(),
                passengerRequest.getPhone()
        );

        return ResponseEntity.ok(passengerRepository.save(passenger));
    }

    public ResponseEntity<Passenger> update(UUID id, PassengerRequest passengerRequest) {
        if (!passengerRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Passenger passenger = passengerRepository.findById(id).get();
        passenger.setEmail(passengerRequest.getEmail());
        passenger.setPhone(passengerRequest.getPhone());
        passenger.setFirstName(passengerRequest.getFirstName());
        passenger.setLastName(passenger.getLastName());

        return ResponseEntity.ok(passengerRepository.save(passenger));
    }

    public ResponseEntity<Void> delete(UUID id){
        if (!passengerRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        passengerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
