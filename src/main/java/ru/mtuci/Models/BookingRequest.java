package ru.mtuci.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class BookingRequest {
    private UUID flightId;
    private UUID passengerId;
    private String seatNumber;
}
