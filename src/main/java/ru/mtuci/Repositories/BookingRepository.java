package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.Entities.Booking;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
