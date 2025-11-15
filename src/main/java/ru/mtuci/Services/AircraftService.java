package ru.mtuci.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Aircraft;
import ru.mtuci.Models.AircraftRequest;
import ru.mtuci.Repositories.AircraftRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AircraftService{
    private final AircraftRepository aircraftRepository;

    public ResponseEntity<Aircraft> findById(UUID uuid){
        if (!aircraftRepository.existsById(uuid)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aircraftRepository.findById(uuid).get());
    }

    public ResponseEntity<List<Aircraft>> findAll(){
        return ResponseEntity.ok(aircraftRepository.findAll());
    }

    public ResponseEntity<Aircraft> create(AircraftRequest aircraftRequest){
        Aircraft aircraft = new Aircraft(
                aircraftRequest.getModel(),
                aircraftRequest.getCapacity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(aircraftRepository.save(aircraft));
    }

    public ResponseEntity<Aircraft> update(UUID uuid, AircraftRequest aircraftRequest){
        if (!aircraftRepository.existsById(uuid)) {
            return ResponseEntity.notFound().build();
        }
        Aircraft aircraft = aircraftRepository.findById(uuid).get();
        aircraft.setCapacity(aircraftRequest.getCapacity());
        aircraft.setModel(aircraftRequest.getModel());
        return ResponseEntity.ok(aircraftRepository.save(aircraft));
    }

    public ResponseEntity<Void> delete(UUID uuid){
        if (!aircraftRepository.existsById(uuid)) {
            return ResponseEntity.notFound().build();
        }

        aircraftRepository.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }

}

