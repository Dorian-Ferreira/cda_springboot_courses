package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.RoomTypeDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.RoomType;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.RoomTypeRepository;
import fr.human_booster.dorian_ferreira.printemps.slugger.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final Slugger slugger;

    public RoomType create(RoomTypeDTO dto) {
        RoomType roomType = dtoToObject(dto, new RoomType());

        return roomTypeRepository.saveAndFlush(roomType);
    }

    public RoomType dtoToObject(RoomTypeDTO roomTypeDTO, RoomType roomType) {
        roomType.setType(roomTypeDTO.getType());
        roomType.setTranslationKey(String.format("room:%s",slugger.slugify(roomTypeDTO.getType())));

        return roomType;
    }

    public void delete(Long id) {
        roomTypeRepository.deleteById(id);
    }

    public RoomType findById(Long id) throws EntityNotFoundException {
        return roomTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("RoomType"));
    }

    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    public Long getOneRandom() {
        return (long)(Math.random() * count())+1;
    }

    public long count() {
        return roomTypeRepository.count();
    }
}
