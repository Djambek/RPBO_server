package ru.mtuci.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mtuci.Entities.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

    @Query(value = "SELECT * FROM flights WHERE flight_number=:number", nativeQuery = true)
    Flight findByFlightNumber(String number);

    @Query(value = "SELECT * FROM flights WHERE departure_time < :time AND arrival_time > :time", nativeQuery = true)
    List<Flight> findUncompletedFlight(LocalDateTime time);
}
