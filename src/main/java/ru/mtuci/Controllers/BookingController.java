package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Booking;
import ru.mtuci.Repositories.BookingRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Бронирование")
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingRepository bookingRepository;

    @GetMapping
    @Operation(summary = "Получение бронирование всех пассажиров")
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение бронирования пассажира по ид бронирования")
    public Booking getById(@PathVariable("id") UUID id) {
        return bookingRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found booking with id: "+ id)
        );
    }

    @PostMapping
    @Operation(summary = "Создание новой брони")
    public Booking create(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingRepository.save(booking)).getBody();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление бронирования пассажира по ид")
    public Booking update(@PathVariable("id") UUID id, @RequestBody Booking booking) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        booking.setId(id);
        return bookingRepository.save(booking);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление бронирования пассажира по ид")
    public void delete(@PathVariable("id") UUID id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }
}
