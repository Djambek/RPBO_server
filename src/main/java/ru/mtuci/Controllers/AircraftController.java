package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;

import ru.mtuci.Entities.Aircraft;
import ru.mtuci.Entities.Airport;
import ru.mtuci.Repositories.AircraftRepository;

import java.util.*;

@RestController
@Tag(name = "Самолеты")
@RequiredArgsConstructor
@RequestMapping("/aircrafts")
public class AircraftController {


    private final AircraftRepository aircraftRepository;


    @GetMapping
    @Operation(summary = "Получение всех самолетов")
    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение самолета по ид")
    public Aircraft getById(@PathVariable("id") UUID id) {
        return aircraftRepository.findById(id).orElseThrow(() -> new RuntimeException("Aircraft not found with id: " + id));
    }

    @PostMapping
    @Operation(summary = "Создание самолета")
    public Aircraft create(@RequestBody Aircraft aircraft) {
        Aircraft savedAircraft = aircraftRepository.save(aircraft);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAircraft).getBody();

    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение самолета")
    public Aircraft update(@PathVariable("id") UUID id, @RequestBody Aircraft aircraft) {
        if (!aircraftRepository.existsById(id)) {
            throw new RuntimeException("Aircraft not found with id: " + id);
        }
        aircraft.setId(id);
       return aircraftRepository.save(aircraft);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление самолета по идшнику")
    public void delete(@PathVariable("id") UUID id) {
        if (!aircraftRepository.existsById(id)) {
            throw new RuntimeException("Aircraft not found with id: " + id);
        }
        aircraftRepository.deleteById(id);
    }
}