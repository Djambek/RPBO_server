package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.Entities.Aircraft;
import ru.mtuci.Entities.Airport;
import ru.mtuci.Entities.Flight;
import ru.mtuci.Entities.Passenger;
import ru.mtuci.Models.*;

import java.time.LocalDateTime;

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

        bookingService.clean();
        flightService.clean();

        aircraftService.clean();
        airportService.clean();
        passengerService.clean();

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> generateTestData(){

        Airport svo = airportService.create(new AirportRequest("SVO", "Шереметьево", "Москва")).getBody();
        Airport led = airportService.create(new AirportRequest("LED", "Пулково", "Санкт-Петербург")).getBody();
        Airport aer = airportService.create(new AirportRequest("AER", "Сочи", "Сочи")).getBody();
        Airport vko = airportService.create(new AirportRequest("VKO", "Внуково", "Москва")).getBody();
        Airport dme = airportService.create(new AirportRequest("DME", "Домодедово", "Москва")).getBody();

        Aircraft b737 = aircraftService.create(new AircraftRequest("Boeing 737-800", 180)).getBody();
        Aircraft a320 = aircraftService.create(new AircraftRequest("Airbus A320", 150)).getBody();
        Aircraft ssj100 = aircraftService.create(new AircraftRequest("Sukhoi Superjet 100", 98)).getBody();
        Aircraft b777 = aircraftService.create(new AircraftRequest("Boeing 777-300", 350)).getBody();

        Passenger p1 = passengerService.create(new PassengerRequest("Иван", "Иванов", "ivan@mail.ru", "+79011234567")).getBody();
        Passenger p2 = passengerService.create(new PassengerRequest("Петр", "Петров", "petr@google.com", "+79027654321")).getBody();
        Passenger p3 = passengerService.create(new PassengerRequest("Мария", "Сидорова", "maria@yandex.ru", "+79038887766")).getBody();
        Passenger p4 = passengerService.create(new PassengerRequest("Анна", "Кузнецова", "anna.k@mail.ru", "+79041112233")).getBody();
        Passenger p5 = passengerService.create(new PassengerRequest("Алексей", "Смирнов", "alex.smirnoff@gmx.com", "+79054445566")).getBody();
        Passenger p6 = passengerService.create(new PassengerRequest("Дмитрий", "Васильев", "d.vasilyev@rambler.ru", "+79067778899")).getBody();

        LocalDateTime now = LocalDateTime.now();

        Flight f1 = (Flight) flightService.create(new FlightRequest(
                "SU-100", svo.getId(), led.getId(),
                now.plusDays(1).withHour(10).withMinute(30),
                now.plusDays(1).withHour(12).withMinute(0),
                a320.getId()
        )).getBody();

        Flight f2 = (Flight) flightService.create(new FlightRequest(
                "DP-205", vko.getId(), aer.getId(),
                now.plusDays(2).withHour(8).withMinute(0),
                now.plusDays(2).withHour(10).withMinute(20),
                ssj100.getId()
        )).getBody();

        Flight f3 = (Flight) flightService.create(new FlightRequest(
                "S7-030", dme.getId(), led.getId(),
                now.plusDays(1).withHour(14).withMinute(0),
                now.plusDays(1).withHour(15).withMinute(30),
                b737.getId()
        )).getBody();

        Flight f4 = (Flight) flightService.create(new FlightRequest(
                "EK-132", dme.getId(), aer.getId(), // Пример: Москва -> Сочи (для теста)
                now.plusDays(10).withHour(16).withMinute(0),
                now.plusDays(10).withHour(18).withMinute(30),
                b777.getId()
        )).getBody();

        Flight f5_past = (Flight) flightService.create(new FlightRequest(
                "UT-101", vko.getId(), led.getId(),
                now.minusDays(1).withHour(10).withMinute(0),
                now.minusDays(1).withHour(11).withMinute(30),
                b737.getId()
        )).getBody();

        Flight f6_past = (Flight) flightService.create(new FlightRequest(
                "AZ-500", svo.getId(), aer.getId(),
                now.minusDays(2).withHour(12).withMinute(0),
                now.minusDays(2).withHour(14).withMinute(20),
                a320.getId()
        )).getBody();



        bookingService.create(new BookingRequest(f1.getId(), p1.getId(), "1A"));
        bookingService.create(new BookingRequest(f1.getId(), p2.getId(), "1B"));
        bookingService.create(new BookingRequest(f1.getId(), p3.getId(), "5C"));

        bookingService.create(new BookingRequest(f2.getId(), p4.getId(), "10A"));
        bookingService.create(new BookingRequest(f2.getId(), p5.getId(), "10B"));

        bookingService.create(new BookingRequest(f3.getId(), p1.getId(), "20F"));
        bookingService.create(new BookingRequest(f3.getId(), p6.getId(), "20E"));

        bookingService.create(new BookingRequest(f4.getId(), p1.getId(), "1C"));
        bookingService.create(new BookingRequest(f4.getId(), p2.getId(), "1D"));
        bookingService.create(new BookingRequest(f4.getId(), p3.getId(), "1E"));
        bookingService.create(new BookingRequest(f4.getId(), p4.getId(), "1F"));
        bookingService.create(new BookingRequest(f4.getId(), p5.getId(), "1G"));

        bookingService.create(new BookingRequest(f5_past.getId(), p1.getId(), "3A"));
        bookingService.create(new BookingRequest(f5_past.getId(), p4.getId(), "3B"));

        bookingService.create(new BookingRequest(f6_past.getId(), p2.getId(), "7D"));
        bookingService.create(new BookingRequest(f6_past.getId(), p3.getId(), "7E"));
        bookingService.create(new BookingRequest(f6_past.getId(), p5.getId(), "7F"));
        bookingService.create(new BookingRequest(f6_past.getId(), p6.getId(), "8A"));

        return ResponseEntity.ok().build();
    }
}
