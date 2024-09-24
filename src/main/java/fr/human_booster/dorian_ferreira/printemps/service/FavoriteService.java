package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.entity.Favorite;
import fr.human_booster.dorian_ferreira.printemps.entity.FavoriteId;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.FavoriteRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService implements ServiceDtoInterface<Favorite, FavoriteId> {
    private FavoriteRepository favoriteRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Favorite create(FavoriteId favoriteDto) {
        Favorite favorite = dtoToObject(favoriteDto, new Favorite());

        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public  Favorite dtoToObject(FavoriteId favoriteDto, Favorite favorite) {

        favorite.setUser(userService.findById(favoriteDto.getUserUuid()));
        favorite.setLodging(lodgingService.findById(favoriteDto.getLodgingUuid()));
        favorite.setId(new FavoriteId(favoriteDto.getLodgingUuid(), favoriteDto.getUserUuid()));

        return favorite;
    }

    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    public Favorite findById(FavoriteId favoriteId) throws EntityNotFoundException {
        return favoriteRepository.findById(favoriteId).orElseThrow(() -> new EntityNotFoundException("Favourite"));
    }

    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    public long count() {
        return favoriteRepository.count();
    }
}
