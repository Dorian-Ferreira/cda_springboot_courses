package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.FavoriteDto;
import fr.human_booster.dorian_ferreira.printemps.dto.LodgingCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Favorite;
import fr.human_booster.dorian_ferreira.printemps.entity.FavoriteId;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.repository.FavoriteRepository;
import fr.human_booster.dorian_ferreira.printemps.repository.LodgingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService {
    private FavoriteRepository favoriteRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Favorite create(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();

        favorite.setUser(userService.getById(favoriteDto.getUserId()));
        favorite.setLodging(lodgingService.getById(favoriteDto.getLodgingId()));

        favorite.setId(new FavoriteId());
        favorite.getId().setLodgingUuid(favoriteDto.getLodgingId());
        favorite.getId().setUserUuid(favoriteDto.getUserId());

        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.saveAndFlush(favorite);
    }

    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }
}
