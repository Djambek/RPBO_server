package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.mtuci.Entities.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    void deleteAllByPassengerId(UUID id);

    @Modifying
    @Query(value = "DELETE FROM bookings WHERE flight_id = :flightId", nativeQuery = true)
    void deleteAllByFlightId(UUID flightId);

    Integer countByFlightId(UUID flightId);

    boolean existsByFlightIdAndPassengerId(UUID flightId, UUID passengerId);

    boolean existsByFlightIdAndSeatNumber(UUID flightId, String seatNumber);

    List<Booking> findByFlightId(UUID id);
}
