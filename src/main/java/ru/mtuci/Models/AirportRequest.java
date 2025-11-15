package ru.mtuci.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class AirportRequest {
    private String code;
    private String name;
    private String city;
}
