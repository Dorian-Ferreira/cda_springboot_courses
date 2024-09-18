package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.repository.LodgingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LodgingService {
    private LodgingRepository lodgingRepository;

    public Lodging create(LodgingCreateDTO lodgingCreateDTO) {
        Lodging lodging = new Lodging();

        lodging.setName(lodgingCreateDTO.getName());
        lodging.setCapacity(lodgingCreateDTO.getCapacity());
        lodging.setAccessible(lodgingCreateDTO.isAccessible());
        lodging.setNightPrice(lodgingCreateDTO.getNightPrice());
        lodging.setSlug(lodgingCreateDTO.getSlug());

        return lodgingRepository.save(lodging);
    }

    public List<Lodging> getAll() {
        return lodgingRepository.findAll();
    }

    public Lodging getById(String lodgingId) {
        return lodgingRepository.findById(lodgingId).orElse(null);
    }
}
