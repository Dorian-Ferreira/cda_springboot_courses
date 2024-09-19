package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.RoomDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Room;
import fr.human_booster.dorian_ferreira.printemps.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public Room create(RoomDTO dto) {
        Room room = new Room();

        room.setType(dto.getType());
        room.setTranslationKey(dto.getTranslationKey());

        return roomRepository.saveAndFlush(room);
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
