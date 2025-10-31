package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Passenger;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Пассажиры")
@RequestMapping("/passengers")
public class PassengerController {
    private final Map<Long, Passenger> passengers = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public PassengerController() {
        // Тестовые данные
        Long id1 = idCounter.getAndIncrement();
        passengers.put(id1, new Passenger(id1, "John", "Doe", "john.doe@email.com", "+1234567890"));

        Long id2 = idCounter.getAndIncrement();
        passengers.put(id2, new Passenger(id2, "Jane", "Smith", "jane.smith@email.com", "+0987654321"));
    }

    @GetMapping
    @Operation(summary = "Получение всех пассажиров")
    public List<Passenger> getAll() {
        return new ArrayList<>(passengers.values());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пассажира по ид")
    public Passenger getById(@PathVariable("id") Long id) {
        Passenger passenger = passengers.get(id);
        if (passenger == null) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        return passenger;
    }

    @PostMapping
    @Operation(summary = "Создание пассажира")
    public Passenger create(@RequestBody Passenger passenger) {
        Long newId = idCounter.getAndIncrement();
        passenger.setId(newId);
        passengers.put(newId, passenger);
        return passenger;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение пассажира по ид")
    public Passenger update(@PathVariable("id") Long id, @RequestBody Passenger passenger) {
        if (!passengers.containsKey(id)) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        passenger.setId(id);
        passengers.put(id, passenger);
        return passenger;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пассажира по ид")
    public void delete(@PathVariable("id") Long id) {
        if (!passengers.containsKey(id)) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        passengers.remove(id);
    }
}
