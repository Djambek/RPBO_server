package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;

import ru.mtuci.Entities.Aircraft;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name = "Самолеты")
@RequestMapping("/aircrafts")
public class AircraftController {
    private final Map<UUID, Aircraft> aircrafts = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public AircraftController() {
        UUID uuid1 = UUID.randomUUID();
        aircrafts.put(uuid1, new Aircraft(uuid1, "Boeing 737", 180));

        UUID uuid2 = UUID.randomUUID();
        aircrafts.put(uuid2, new Aircraft(uuid2, "Airbus A320", 150));
    }

    @GetMapping
    @Operation(summary = "Получение всех самолетов")
    public List<Aircraft> getAll() {
        return new ArrayList<>(aircrafts.values());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение самолета по ид")
    public Aircraft getById(@PathVariable("id") UUID id) {
        Aircraft aircraft = aircrafts.get(id);
        if (aircraft == null) {
            throw new RuntimeException("Aircraft not found with id: " + id);
        }
        return aircraft;
    }

    @PostMapping
    @Operation(summary = "Создание самолета")
    public Aircraft create(@RequestBody Aircraft aircraft) {
        UUID uuid = UUID.randomUUID();
        aircrafts.put(uuid, aircraft);
        return aircraft;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение самолета")
    public Aircraft update(@PathVariable("id") UUID id, @RequestBody Aircraft aircraft) {
        if (!aircrafts.containsKey(id)) {
            throw new RuntimeException("Aircraft not found with id: " + id);
        }
        UUID uuid = UUID.randomUUID();
        aircrafts.put(uuid, aircraft);
        return aircraft;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление самолета по идшнику")
    public void delete(@PathVariable("id") UUID id) {
        if (!aircrafts.containsKey(id)) {
            throw new RuntimeException("Aircraft not found with id: " + id);
        }
        aircrafts.remove(id);
    }
}