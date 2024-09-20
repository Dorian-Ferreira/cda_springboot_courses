package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.MediaDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Media;
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
        Media media = dtoToObject(dto, new Media());

        media.setLodging(lodgingService.findById(lodgingId));

        return mediaRepository.saveAndFlush(media);
    }

    @Override
    public  Media dtoToObject(MediaDTO mediaDTO, Media media) {
        media.setPath(mediaDTO.getPath());
        media.setExtension(mediaDTO.getExtension());

        return media;
    }

    public void delete(Media media) {
        mediaRepository.delete(media);
    }

    public Media findById(Long id) {
        return mediaRepository.findById(id).orElseThrow();
    }

    public List<Media> findAll() {
        return mediaRepository.findAll();
    }
}
