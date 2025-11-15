package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Passenger;
import ru.mtuci.Models.PassengerRequest;
import ru.mtuci.Services.PassengerService;

import java.util.*;

@RestController
@Tag(name="Пассажиры")
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    @Operation(summary = "Получение всех пассажиров")
    public ResponseEntity<List<Passenger>> getAll() {
        return passengerService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пассажира по ид")
    public ResponseEntity<Passenger> getById(@PathVariable("id") UUID id) {
        return passengerService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создание пассажира")
    public ResponseEntity<Passenger> create(@RequestBody PassengerRequest request) {
        return passengerService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение пассажира по ид")
    public ResponseEntity<Passenger> update(@PathVariable("id") UUID id, @RequestBody PassengerRequest request) {
        return passengerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пассажира по ид")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return passengerService.delete(id);
    }
}
