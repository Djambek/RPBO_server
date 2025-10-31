package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Booking;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Бронирование")
@RequestMapping("/bookings")
public class BookingController {
    private final Map<Long, Booking> bookings = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public BookingController() {
        // Тестовые данные
        Long id1 = idCounter.getAndIncrement();
        bookings.put(id1, new Booking(id1, 1L, 1L, "15A"));

        Long id2 = idCounter.getAndIncrement();
        bookings.put(id2, new Booking(id2, 1L, 2L, "15B"));

        Long id3 = idCounter.getAndIncrement();
        bookings.put(id3, new Booking(id3, 2L, 1L, "8C"));
    }

    @GetMapping
    @Operation(summary = "Получение бронирование всех пассажиров")
    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение бронирования пассажира по ид")
    public Booking getById(@PathVariable("id") Long id) {
        Booking booking = bookings.get(id);
        if (booking == null) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        return booking;
    }

    @PostMapping
    @Operation(summary = "Создание новой брони")
    public Booking create(@RequestBody Booking booking) {
        Long newId = idCounter.getAndIncrement();
        booking.setId(newId);
        bookings.put(newId, booking);
        return booking;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление бронирования пассажира по ид")
    public Booking update(@PathVariable("id") Long id, @RequestBody Booking booking) {
        if (!bookings.containsKey(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        booking.setId(id);
        bookings.put(id, booking);
        return booking;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление бронирования пассажира по ид")
    public void delete(@PathVariable("id") Long id) {
        if (!bookings.containsKey(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        bookings.remove(id);
    }
}
