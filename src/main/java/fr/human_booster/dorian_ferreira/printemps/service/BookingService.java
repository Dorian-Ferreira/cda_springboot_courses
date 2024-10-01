package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.BookingDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.BookingLoggedDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Booking;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.exception.LodgingUnavailableException;
import fr.human_booster.dorian_ferreira.printemps.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Booking create(BookingLoggedDTO dto, Principal principal) {
        Booking booking = dtoToObject(dto, new Booking());
        booking.setUser(userService.findByEmail(principal.getName()));

        if(isAvailable(dto)) {
            return bookingRepository.saveAndFlush(booking);
        }
        throw new LodgingUnavailableException();
    }

    public Booking createInit(BookingDTO dto) {
        Booking booking = dtoToObject(dto, new Booking());

        booking.setUser(userService.findById(dto.getUserUuid()));

        return bookingRepository.save(booking);
    }

    public void flush() {
        bookingRepository.flush();
    }

    public Boolean isAvailable(BookingLoggedDTO bookingLoggedDTO) {
        return lodgingService.isAvailable(
                lodgingService.findById(bookingLoggedDTO.getLodgingUuid()),
                bookingLoggedDTO.getStartedAt(),
                bookingLoggedDTO.getFinishedAt(),
                bookingLoggedDTO.getQuantity());
    }

    public  Booking dtoToObject(BookingLoggedDTO dto, Booking booking) {
        booking.setNumber("Booking" + (bookingRepository.count() + 1));

        booking.setIsCanceled(false);

        booking.setCreatedAt(LocalDateTime.now());

        booking.setStartedAt(dto.getStartedAt());
        booking.setFinishedAt(dto.getFinishedAt());
        booking.setQuantity(dto.getQuantity());

        booking.setLodging(lodgingService.findById(dto.getLodgingUuid()));

        return booking;
    }

    public boolean delete(String uuid) {
        Booking booking = findById(uuid);

        if(booking.getStartedAt().isBefore(LocalDateTime.now())) {
            return false;
        }

        booking.setIsCanceled(true);

        bookingRepository.saveAndFlush(booking);
        return true;
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

    public List<Booking> findAllByLodging(String uuid) {
        return bookingRepository.findAllByLodgingUuidAndIsCanceledNullAndStartedAtAfter(uuid, LocalDateTime.now());
    }
}
