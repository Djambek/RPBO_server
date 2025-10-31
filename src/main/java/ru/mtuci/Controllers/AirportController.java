package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Airport;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Аэропорты")
@RequestMapping("/airports")
public class AirportController {
    private final Map<Long, Airport> airports = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public AirportController() {
        Long id1 = idCounter.getAndIncrement();
        airports.put(id1, new Airport(id1, "SVO", "Sheremetyevo", "Moscow"));

        Long id2 = idCounter.getAndIncrement();
        airports.put(id2, new Airport(id2, "JFK", "John F. Kennedy", "New York"));

        Long id3 = idCounter.getAndIncrement();
        airports.put(id3, new Airport(id3, "LED", "Pulkovo", "Saint Petersburg"));
    }

    @GetMapping
    @Operation(summary = "Получение всех аэропортов")
    public List<Airport> getAll() {
        return new ArrayList<>(airports.values());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение аэропорта по ид")
    public Airport getById(@PathVariable("id") Long id) {
        System.out.println(id);
        Airport airport = airports.get(id);
        if (airport == null) {
            throw new RuntimeException("Airport not found with id: " + id);
        }
        return airport;
    }

    @PostMapping
    @Operation(summary = "Создание аэропорта")
    public Airport create(@RequestBody Airport airport) {
        Long newId = idCounter.getAndIncrement();
        airport.setId(newId);
        airports.put(newId, airport);
        return airport;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение аэропорта по ид")
    public Airport update(@PathVariable("id") Long id, @RequestBody Airport airport) {
        if (!airports.containsKey(id)) {
            throw new RuntimeException("Airport not found with id: " + id);
        }
        airport.setId(id);
        airports.put(id, airport);
        return airport;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление аэропорта по ид")
    public void delete(@PathVariable("id") Long id) {
        if (!airports.containsKey(id)) {
            throw new RuntimeException("Airport not found with id: " + id);
        }
        airports.remove(id);
    }
}