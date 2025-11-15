package ru.mtuci.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.Entities.Passenger;
import ru.mtuci.Repositories.PassangerRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Tag(name="Пассажиры")
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassangerRepository passangerRepository;



    @GetMapping
    @Operation(summary = "Получение всех пассажиров")
    public List<Passenger> getAll() {
        return passangerRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пассажира по ид")
    public Passenger getById(@PathVariable("id") UUID id) {
        return passangerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found passenger with id: " + id)
        );
    }

    @PostMapping
    @Operation(summary = "Создание пассажира")
    public Passenger create(@RequestBody Passenger passenger) {
        return ResponseEntity.status(HttpStatus.CREATED).body(passangerRepository.save(passenger)).getBody();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение пассажира по ид")
    public Passenger update(@PathVariable("id") UUID id, @RequestBody Passenger passenger) {
        if (!passangerRepository.existsById(id)) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        passenger.setId(id);
        return passangerRepository.save(passenger);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пассажира по ид")
    public void delete(@PathVariable("id") UUID id) {
        if (!passangerRepository.existsById(id)) {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
        passangerRepository.deleteById(id);
    }
}
