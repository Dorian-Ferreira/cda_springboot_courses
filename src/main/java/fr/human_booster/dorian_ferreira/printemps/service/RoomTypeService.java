package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.RoomTypeDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.RoomType;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.RoomTypeRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomTypeService implements ServiceDtoInterface<RoomType, RoomTypeDTO> {

    private RoomTypeRepository roomTypeRepository;

    public RoomType create(RoomTypeDTO dto) {
        RoomType roomType = dtoToObject(dto, new RoomType());

        return roomTypeRepository.saveAndFlush(roomType);
    }

    @Override
    public RoomType dtoToObject(RoomTypeDTO roomTypeDTO, RoomType roomType) {
        roomType.setType(roomTypeDTO.getType());
        roomType.setTranslationKey(roomTypeDTO.getTranslationKey());

        return roomType;
    }

    public void delete(RoomType roomType) {
        roomTypeRepository.delete(roomType);
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
