package ru.mtuci.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class PassengerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
