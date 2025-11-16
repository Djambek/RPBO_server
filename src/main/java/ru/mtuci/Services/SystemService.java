package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final AirportService airportService;
    private final AircraftService aircraftService;
    private final BookingService bookingService;
    private final FlightService flightService;
    private final PassengerService passengerService;

    @Transactional
    public ResponseEntity<Void> clearDB(){
        aircraftService.clean();
        airportService.clean();
        bookingService.clean();
        flightService.clean();
        passengerService.clean();

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> generateTestData(){



        return ResponseEntity.ok().build();
    }
}
