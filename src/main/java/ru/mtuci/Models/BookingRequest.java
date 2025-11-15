package ru.mtuci.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BookingRequest {
    private UUID flightId;
    private UUID passengerId;
    private String seatNumber;
}
