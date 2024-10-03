package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.RoomType;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.LodgingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LodgingService {

    private LodgingRepository lodgingRepository;
    private AddressService addressService;
    private RoomTypeService roomTypeService;

    public Lodging create(LodgingCreateDTO lodgingCreateDTO) {
        Lodging lodging = dtoToObject(lodgingCreateDTO, new Lodging());
        lodging.setIsOpen(true);

        if(lodgingCreateDTO.getAddressId() != null) {
            try {
                lodging.setAddress(addressService.findById(lodgingCreateDTO.getAddressId()));
            } catch (EntityNotFoundException e) {
                return null;
            }
        } else if(lodgingCreateDTO.getAddressDTO() != null){
            lodging.setAddress(addressService.create(lodgingCreateDTO.getAddressDTO()));
        }

        return lodgingRepository.saveAndFlush(lodging);
    }

    public Lodging createInit(LodgingCreateDTO lodgingCreateDTO) {
        Lodging lodging = dtoToObject(lodgingCreateDTO, new Lodging());
        lodging.setIsOpen(true);

        if(lodgingCreateDTO.getAddressId() != null) {
            try {
                lodging.setAddress(addressService.findById(lodgingCreateDTO.getAddressId()));
            } catch (EntityNotFoundException e) {
                return null;
            }
        } else if(lodgingCreateDTO.getAddressDTO() != null){
            lodging.setAddress(addressService.createInit(lodgingCreateDTO.getAddressDTO()));
        }

        return lodgingRepository.save(lodging);
    }

    public Lodging update(LodgingUpdateDTO lodgingUpdateDTO, String uuid) {
        try {
            Lodging lodging = dtoToObject(lodgingUpdateDTO, findById(uuid));
            return lodgingRepository.saveAndFlush(lodging);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Transactional
    public void addRoomTypeInit(String uuid, Long id) {
        try {
            Lodging lodging = findById(uuid);
            RoomType roomType = roomTypeService.findById(id);

            lodging.handleRoom(roomType);

            lodgingRepository.save(lodging);
        } catch (EntityNotFoundException ignored) {

        }
    }

    @Transactional
    public Lodging handleRoomType(String uuid, Long id) {
        try {
            Lodging lodging = findById(uuid);
            RoomType roomType = roomTypeService.findById(id);

            lodging.handleRoom(roomType);

            return lodgingRepository.saveAndFlush(lodging);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public  Lodging dtoToObject(LodgingUpdateDTO lodgingUpdateDTO, Lodging lodging) {
        lodging.setName(lodgingUpdateDTO.getName());

        // DO NOT DELETE
        lodging.setSlug("ça marche pas si je mets pas ça wtf");

        lodging.setCapacity(lodgingUpdateDTO.getCapacity());
        lodging.setDescription(lodgingUpdateDTO.getDescription());
        lodging.setIsAccessible(lodgingUpdateDTO.getIsAccessible());
        lodging.setNightPrice(lodgingUpdateDTO.getNightPrice());

        return lodging;
    }


    public boolean delete(String id) {
        Lodging lodging = findById(id);

        lodging.setIsOpen(false);
        lodgingRepository.saveAndFlush(lodging);

        return true;
    }

    public List<Lodging> findAll() {
        return lodgingRepository.findAll();
    }

    public List<Lodging> findAllOpen() {
        return lodgingRepository.findAllByIsOpenIsTrue();
    }

    public List<Lodging> searchAllOpen(LocalDate startDate, LocalDate endDate, Integer nightPrice, Integer capacity, Boolean isAccessible) {
        return (isAccessible == null || !isAccessible) ?
            lodgingRepository.findAllBySearch(
                startDate != null ? startDate.atStartOfDay() : null,
                endDate != null ? endDate.atStartOfDay() : null,
                startDate != null ? startDate.atStartOfDay() : null,
                endDate != null ? endDate.atStartOfDay() : null,
                (nightPrice == null || nightPrice <= 0) ? Integer.MAX_VALUE : nightPrice,
                (capacity == null || capacity <=0) ? 1 : capacity
            ) :
            lodgingRepository.findAllAccessibleBySearch(
                startDate != null ? startDate.atStartOfDay() : null,
                endDate != null ? endDate.atStartOfDay() : null,
                startDate != null ? startDate.atStartOfDay() : null,
                endDate != null ? endDate.atStartOfDay() : null,
                (nightPrice == null || nightPrice <= 0) ? Integer.MAX_VALUE : nightPrice,
                (capacity == null || capacity <=0) ? 1 : capacity
             );
    }

    public Lodging findById(String lodgingId) {
        return lodgingRepository.findByUuidAndIsOpenIsTrue(lodgingId).orElseThrow(() -> new EntityNotFoundException("Lodging"));
    }

    public long count() {
        return lodgingRepository.count();
    }

    public Lodging getOneRandom() {
        return lodgingRepository.findRandom();
    }

    public void flush() {
        lodgingRepository.flush();
    }

    public Lodging findBySlug(String slug) {
        return lodgingRepository.findBySlug(slug).orElseThrow(() -> new EntityNotFoundException("Lodging"));
    }

    public List<Lodging> findAllOpenByName(String name) {
        return lodgingRepository.findAllByIsOpenIsTrueAndNameContainingIgnoreCase(name);
    }

    public Boolean isAvailable(Lodging lodging, LocalDateTime startedAt, LocalDateTime finishedAt, int quantity) {
        return lodgingRepository.findAllBySearch(
                startedAt,
                finishedAt,
                startedAt,
                finishedAt,
                quantity)
            .contains(lodging);
    }
}
