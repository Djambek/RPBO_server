package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Airport;
import ru.mtuci.Models.AirportRequest;
import ru.mtuci.Services.AirportService;

import java.util.*;

@RestController
@Tag(name="Аэропорты")
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;


    @GetMapping
    @Operation(summary = "Получение всех аэропортов")
    public ResponseEntity<List<Airport>> getAll() {
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение аэропорта по ид")
    public ResponseEntity<Airport> getById(@PathVariable("id") UUID id) {
        return airportService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создание аэропорта")
    public ResponseEntity<Airport> create(@RequestBody AirportRequest request) {
        return airportService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение аэропорта по ид")
    public ResponseEntity<Airport> update(@PathVariable("id") UUID id, @RequestBody AirportRequest request) {
        return airportService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление аэропорта по ид")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return airportService.delete(id);
    }
}