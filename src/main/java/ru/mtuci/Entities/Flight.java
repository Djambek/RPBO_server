package ru.mtuci.Entities;

import java.time.LocalDateTime;

public class Flight {
    private Long id;
    private String flightNumber;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long aircraftId;

    public Flight() {}

    public Flight(Long id, String flightNumber, Long departureAirportId,
                  Long arrivalAirportId, LocalDateTime departureTime,
                  LocalDateTime arrivalTime, Long aircraftId) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraftId = aircraftId;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public Long getDepartureAirportId() { return departureAirportId; }
    public void setDepartureAirportId(Long departureAirportId) { this.departureAirportId = departureAirportId; }
    public Long getArrivalAirportId() { return arrivalAirportId; }
    public void setArrivalAirportId(Long arrivalAirportId) { this.arrivalAirportId = arrivalAirportId; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public Long getAircraftId() { return aircraftId; }
    public void setAircraftId(Long aircraftId) { this.aircraftId = aircraftId; }
}
