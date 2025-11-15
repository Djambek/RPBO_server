package ru.mtuci.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AircraftRequest {
    private String model;
    private int capacity;
}
