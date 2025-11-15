package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.Entities.Aircraft;

import java.util.UUID;

public interface AircraftRepository extends JpaRepository<Aircraft, UUID> {
}
