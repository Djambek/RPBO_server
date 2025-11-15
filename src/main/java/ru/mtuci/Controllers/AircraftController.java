package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;

import ru.mtuci.Entities.Aircraft;
import ru.mtuci.Models.AircraftRequest;
import ru.mtuci.Services.AircraftService;

import java.util.*;

@RestController
@Tag(name = "Самолеты")
@RequiredArgsConstructor
@RequestMapping("/aircrafts")
public class AircraftController {
    private final AircraftService aircraftService;


    @GetMapping
    @Operation(summary = "Получение всех самолетов")
    public ResponseEntity<List<Aircraft>> getAll() {
        return aircraftService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение самолета по ид")
    public ResponseEntity<Aircraft> getById(@PathVariable("id") UUID id) {
       return aircraftService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создание самолета")
    public ResponseEntity<Aircraft> create(@RequestBody AircraftRequest request) {
        return aircraftService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение самолета")
    public ResponseEntity<Aircraft> update(@PathVariable("id") UUID id, @RequestBody AircraftRequest request) {
       return aircraftService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление самолета по идшнику")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return aircraftService.delete(id);
    }
}