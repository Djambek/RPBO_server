package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Flight;
import ru.mtuci.Models.FlightRequest;
import ru.mtuci.Services.FlightService;

import java.util.*;

@RestController
@Tag(name="Полеты")
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService
            ;

    @GetMapping
    @Operation(summary = "Получение всех полетов")
    public ResponseEntity<List<Flight>> getAll() {
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение полета по ид")
    public ResponseEntity<Flight> getById(@PathVariable("id") UUID id) {
        return flightService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создание полета")
    public ResponseEntity<Flight> create(@RequestBody FlightRequest request) {
        return flightService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение полета по ид")
    public ResponseEntity<Flight> update(@PathVariable("id") UUID id, @RequestBody FlightRequest request) {
        return flightService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление полета по ид")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return flightService.delete(id);
    }
}
