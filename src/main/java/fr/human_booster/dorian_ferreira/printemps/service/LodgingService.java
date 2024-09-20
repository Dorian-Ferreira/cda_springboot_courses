package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.AddressDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Address;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.RoomType;
import fr.human_booster.dorian_ferreira.printemps.repository.LodgingRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LodgingService implements ServiceDtoInterface<Lodging, LodgingUpdateDTO> {

    private LodgingRepository lodgingRepository;
    private AddressService addressService;
    private RoomTypeService roomTypeService;

    public Lodging create(LodgingCreateDTO lodgingCreateDTO) {
        Lodging lodging = dtoToObject(lodgingCreateDTO, new Lodging());

        if(lodgingCreateDTO.getAddressId() != null) {
            lodging.setAddress(addressService.findById(lodgingCreateDTO.getAddressId()));
        } else if(lodgingCreateDTO.getAddressDTO() != null){
            lodging.setAddress(addressService.create(lodgingCreateDTO.getAddressDTO()));
        }

        return lodgingRepository.saveAndFlush(lodging);
    }

    public Lodging update(LodgingUpdateDTO lodgingUpdateDTO, String uuid) {
        Lodging lodging = dtoToObject(lodgingUpdateDTO, findById(uuid));

        return lodgingRepository.saveAndFlush(lodging);
    }

    @Transactional
    public Lodging addRoomType(String uuid, Long id) {
        Lodging lodging = findById(uuid);
        System.out.println(id);
        RoomType roomType = roomTypeService.findById(id);

        lodging.addRoom(roomType);

        return lodgingRepository.saveAndFlush(lodging);
    }

    @Override
    public  Lodging dtoToObject(LodgingUpdateDTO lodgingUpdateDTO, Lodging lodging) {
        lodging.setName(lodgingUpdateDTO.getName());

        // DO NOT DELETE
        lodging.setSlug("ça marche pas si je mets pas ça wtf");

        lodging.setCapacity(lodgingUpdateDTO.getCapacity());
        lodging.setDescription(lodgingUpdateDTO.getDescription());
        lodging.setAccessible(lodgingUpdateDTO.isAccessible());
        lodging.setNightPrice(lodgingUpdateDTO.getNightPrice());

        return lodging;
    }


    public void delete(Lodging lodging) {
        lodgingRepository.delete(lodging);
    }

    public List<Lodging> findAll() {
        return lodgingRepository.findAll();
    }

    public Lodging findById(String lodgingId) {
        return lodgingRepository.findById(lodgingId).orElse(null);
    }

    public long count() {
        return lodgingRepository.count();
    }

    public Lodging getOneRandom() {
        return lodgingRepository.findRandom();
    }
}
