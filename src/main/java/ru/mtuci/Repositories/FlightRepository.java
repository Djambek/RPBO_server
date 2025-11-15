package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.Entities.Flight;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
