package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.LodgingDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.repository.LodgingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LodgingService {
    private LodgingRepository lodgingRepository;
    private AddressService addressService;

    public Lodging create(LodgingDTO lodgingDTO) {
        Lodging lodging = new Lodging();

        lodging.setName(lodgingDTO.getName());
        lodging.setCapacity(lodgingDTO.getCapacity());
        lodging.setAccessible(lodgingDTO.isAccessible());
        lodging.setDescription(lodgingDTO.getDescription());
        lodging.setNightPrice(lodgingDTO.getNightPrice());
        lodging.setSlug(lodgingDTO.getName());

        if(lodgingDTO.getAddressId() != null) {
            lodging.setAddress(addressService.findById(lodgingDTO.getAddressId()));
        } else {
            lodging.setAddress(addressService.create(lodgingDTO.getAddressDTO()));
        }

        return lodgingRepository.saveAndFlush(lodging);
    }

    public Lodging update(LodgingUpdateDTO lodgingUpdateDTO, String uuid) {
        Lodging lodging = findById(uuid);

        if(!lodgingUpdateDTO.getName().equals(lodging.getName())) {
            lodging.setName(lodgingUpdateDTO.getName());
            lodging.setSlug(lodgingUpdateDTO.getName());
        }

        if(lodgingUpdateDTO.getCapacity() != lodging.getCapacity())
            lodging.setCapacity(lodgingUpdateDTO.getCapacity());

        if(!lodgingUpdateDTO.getDescription().equals(lodging.getDescription()))
            lodging.setDescription(lodgingUpdateDTO.getDescription());

        if(lodgingUpdateDTO.isAccessible() != lodging.isAccessible())
            lodging.setAccessible(lodgingUpdateDTO.isAccessible());

        if(lodgingUpdateDTO.getNightPrice() != lodging.getNightPrice())
            lodging.setNightPrice(lodgingUpdateDTO.getNightPrice());

        lodging.setSlug(lodgingUpdateDTO.getName());

        return lodgingRepository.saveAndFlush(lodging);
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
}
