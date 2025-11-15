package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Flight;
import ru.mtuci.Repositories.FlightRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Полеты")
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightRepository flightRepository;

    @GetMapping
    @Operation(summary = "Получение всех полетов")
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение полета по ид")
    public Flight getById(@PathVariable("id") UUID id) {
        return flightRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found flight with id: " + id)
        );
    }

    @PostMapping
    @Operation(summary = "Создание полета")
    public Flight create(@RequestBody Flight flight) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightRepository.save(flight)).getBody();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение полета по ид")
    public Flight update(@PathVariable("id") UUID id, @RequestBody Flight flight) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found with id: " + id);
        }
        flight.setId(id);
        return flightRepository.save(flight);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление полета по ид")
    public void delete(@PathVariable("id") UUID id) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }
}
