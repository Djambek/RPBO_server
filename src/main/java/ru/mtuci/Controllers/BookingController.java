package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Booking;
import ru.mtuci.Models.BookingRequest;
import ru.mtuci.Services.BookingService;

import java.util.*;

@RestController
@Tag(name="Бронирование")
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    @Operation(summary = "Получение бронирование всех пассажиров")
    public ResponseEntity<List<Booking>> getAll() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение бронирования пассажира по ид бронирования")
    public ResponseEntity<Booking> getById(@PathVariable("id") UUID id) {
        return bookingService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создание новой брони")
    public ResponseEntity<Booking> create(@RequestBody BookingRequest request) {
        return bookingService.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление бронирования пассажира по ид")
    public ResponseEntity<Booking> update(@PathVariable("id") UUID id, @RequestBody BookingRequest request) {
        return bookingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление бронирования пассажира по ид")
    public void delete(@PathVariable("id") UUID id) {
        bookingService.delete(id);
    }
}
