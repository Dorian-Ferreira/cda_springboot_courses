package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.AddressDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.BookingDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Address;
import fr.human_booster.dorian_ferreira.printemps.entity.Booking;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.BookingRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService implements ServiceDtoInterface<Booking, BookingDTO> {

    private BookingRepository bookingRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Booking create(BookingDTO dto) {
        Booking booking = dtoToObject(dto, new Booking());

        return bookingRepository.saveAndFlush(booking);
    }

    @Override
    public  Booking dtoToObject(BookingDTO dto, Booking booking) {
        booking.setNumber("Booking" + (bookingRepository.count() + 1));

        booking.setCreatedAt(LocalDateTime.now());

        booking.setStartedAt(dto.getStartedAt());
        booking.setFinishedAt(dto.getFinishedAt());
        booking.setQuantity(dto.getQuantity());

        booking.setUser(userService.findById(dto.getUserUuid()));
        booking.setLodging(lodgingService.findById(dto.getLodgingUuid()));

        return booking;
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    public Booking findById(String id) throws EntityNotFoundException {
        return bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking"));
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public long count() {
        return bookingRepository.count();
    }
}
