package ru.mtuci.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class FlightRequest {
    private String flightNumber;
    private UUID departureAirportId;
    private UUID arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private UUID aircraftId;
}
