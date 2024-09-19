package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.entity.Favorite;
import fr.human_booster.dorian_ferreira.printemps.entity.FavoriteId;
import fr.human_booster.dorian_ferreira.printemps.repository.FavoriteRepository;
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

    public Favorite create(FavoriteId favoriteDto) {
        Favorite favorite = new Favorite();

        favorite.setUser(userService.findById(favoriteDto.getUserUuid()));
        favorite.setLodging(lodgingService.findById(favoriteDto.getLodgingUuid()));
        favorite.setId(new FavoriteId(favoriteDto.getLodgingUuid(), favoriteDto.getUserUuid()));

        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.saveAndFlush(favorite);
    }

    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    public Favorite findById(FavoriteId favoriteId) {
        return favoriteRepository.findById(favoriteId).orElseThrow();
    }

    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }
}
