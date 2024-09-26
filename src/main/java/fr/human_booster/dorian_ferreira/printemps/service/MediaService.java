package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.MediaDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Media;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.MediaRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MediaService implements ServiceDtoInterface<Media, MediaDTO> {

    private MediaRepository mediaRepository;
    private LodgingService lodgingService;

    public Media create(MediaDTO dto, String lodgingId) {
        try {

            Media media = dtoToObject(dto, new Media());

            media.setLodging(lodgingService.findById(lodgingId));

            return mediaRepository.saveAndFlush(media);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public  Media dtoToObject(MediaDTO mediaDTO, Media media) {
        media.setPath(mediaDTO.getPath());
        media.setExtension(mediaDTO.getExtension());

        return media;
    }

    public void delete(Long id) {
        mediaRepository.deleteById(id);
    }

    public Media findById(Long id) throws EntityNotFoundException {
        return mediaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Media"));
    }

    public List<Media> findAll() {
        return mediaRepository.findAll();
    }
}
