package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.AddressDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.RoomDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Address;
import fr.human_booster.dorian_ferreira.printemps.entity.Room;
import fr.human_booster.dorian_ferreira.printemps.repository.RoomRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService implements ServiceDtoInterface<Room, RoomDTO> {

    private RoomRepository roomRepository;

    public Room create(RoomDTO dto) {
        Room room = dtoToObject(dto, new Room());

        return roomRepository.saveAndFlush(room);
    }

    @Override
    public  Room dtoToObject(RoomDTO roomDTO, Room room) {
        room.setType(roomDTO.getType());
        room.setTranslationKey(roomDTO.getTranslationKey());

        return room;
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
