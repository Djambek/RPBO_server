package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mtuci.Entities.Airport;

import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {
}