package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.Entities.Passenger;

import java.util.UUID;

public interface PassengerRepository extends JpaRepository<Passenger, UUID> {
}
