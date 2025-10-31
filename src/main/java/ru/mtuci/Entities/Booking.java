package ru.mtuci.Entities;

public class Booking {
    private Long id;
    private Long flightId;
    private Long passengerId;
    private String seatNumber;

    public Booking() {}

    public Booking(Long id, Long flightId, Long passengerId, String seatNumber) {
        this.id = id;
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.seatNumber = seatNumber;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Long getPassengerId() { return passengerId; }
    public void setPassengerId(Long passengerId) { this.passengerId = passengerId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
}
