package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Flight;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Полеты")
@RequestMapping("/flights")
public class FlightController {
    private final Map<Long, Flight> flights = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public FlightController() {
        Long id1 = idCounter.getAndIncrement();
        flights.put(id1, new Flight(id1, "SU100", 1L, 2L,
                LocalDateTime.of(2024, 1, 15, 10, 0),
                LocalDateTime.of(2024, 1, 15, 14, 30), 1L));

        Long id2 = idCounter.getAndIncrement();
        flights.put(id2, new Flight(id2, "SU200", 3L, 1L,
                LocalDateTime.of(2024, 1, 16, 15, 0),
                LocalDateTime.of(2024, 1, 16, 16, 30), 2L));
    }

    @GetMapping
    @Operation(summary = "Получение всех полетов")
    public List<Flight> getAll() {
        return new ArrayList<>(flights.values());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение полета по ид")
    public Flight getById(@PathVariable("id") Long id) {
        Flight flight = flights.get(id);
        if (flight == null) {
            throw new RuntimeException("Flight not found with id: " + id);
        }
        return flight;
    }

    @PostMapping
    @Operation(summary = "Создание полета")
    public Flight create(@RequestBody Flight flight) {
        Long newId = idCounter.getAndIncrement();
        flight.setId(newId);
        flights.put(newId, flight);
        return flight;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение полета по ид")
    public Flight update(@PathVariable("id") Long id, @RequestBody Flight flight) {
        if (!flights.containsKey(id)) {
            throw new RuntimeException("Flight not found with id: " + id);
        }
        flight.setId(id);
        flights.put(id, flight);
        return flight;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление полета по ид")
    public void delete(@PathVariable("id") Long id) {
        if (!flights.containsKey(id)) {
            throw new RuntimeException("Flight not found with id: " + id);
        }
        flights.remove(id);
    }
}
