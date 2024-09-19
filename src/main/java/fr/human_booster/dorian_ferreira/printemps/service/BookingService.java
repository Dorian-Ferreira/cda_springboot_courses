package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.BookingDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Booking;
import fr.human_booster.dorian_ferreira.printemps.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Booking create(BookingDTO dto) {
        Booking booking = new Booking();

        booking.setNumber("Booking" + (bookingRepository.count() + 1));

        booking.setCreatedAt(LocalDateTime.now());

        booking.setStartedAt(dto.getStartedAt());
        booking.setFinishedAt(dto.getFinishedAt());
        booking.setQuantity(dto.getQuantity());

        booking.setUser(userService.findById(dto.getUserUuid()));
        booking.setLodging(lodgingService.findById(dto.getLodgingUuid()));

        return bookingRepository.saveAndFlush(booking);
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    public Booking findById(String id) {
        return bookingRepository.findById(id).orElseThrow();
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }
}
