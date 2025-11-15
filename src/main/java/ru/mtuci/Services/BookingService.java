package ru.mtuci.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.mtuci.Entities.Booking;
import ru.mtuci.Models.BookingRequest;
import ru.mtuci.Repositories.BookingRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public ResponseEntity<Booking> findById(UUID id){
        if (!bookingRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingRepository.findById(id).get());
    }

    public ResponseEntity<List<Booking>> findAll(){
        return ResponseEntity.ok((bookingRepository.findAll()));
    }

    public ResponseEntity<Booking> create(BookingRequest bookingRequest){
        Booking booking = new Booking(
                bookingRequest.getFlightId(),
                bookingRequest.getPassengerId(),
                bookingRequest.getSeatNumber()
        );
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    public ResponseEntity<Booking> update(UUID id, BookingRequest bookingRequest){
        if (!bookingRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Booking booking = bookingRepository.findById(id).get();
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setPassengerId(bookingRequest.getPassengerId());
        booking.setSeatNumber(bookingRequest.getSeatNumber());

        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!bookingRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
