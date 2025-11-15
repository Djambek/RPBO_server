package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Airport;
import ru.mtuci.Repositories.AirportRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Аэропорты")
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportRepository airportRepository;


    @GetMapping
    @Operation(summary = "Получение всех аэропортов")
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение аэропорта по ид")
    public Airport getById(@PathVariable("id") UUID id) {
        return airportRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Not found airport with id: " + id));
    }

    @PostMapping
    @Operation(summary = "Создание аэропорта")
    public Airport create(@RequestBody Airport airport) {
      return ResponseEntity.status(HttpStatus.CREATED).body(airportRepository.save(airport)).getBody();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение аэропорта по ид")
    public Airport update(@PathVariable("id") UUID id, @RequestBody Airport airport) {
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport not found with id: " + id);
        }
        airport.setId(id);
        return airportRepository.save(airport);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление аэропорта по ид")
    public void delete(@PathVariable("id") UUID id) {
        if (!airportRepository.existsById(id)) {
            throw new RuntimeException("Airport not found with id: " + id);
        }
        airportRepository.deleteById(id);
    }
}