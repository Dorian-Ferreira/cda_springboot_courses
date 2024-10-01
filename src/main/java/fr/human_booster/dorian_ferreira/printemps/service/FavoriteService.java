package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.entity.Favorite;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.entity.embeddedId.UserLodgingId;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
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

    public Favorite create(UserLodgingId favoriteDto) {
        Favorite favorite = dtoToObject(favoriteDto, new Favorite());

        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.saveAndFlush(favorite);
    }

    public void createInit(UserLodgingId favoriteDto) {
        Favorite favorite = dtoToObject(favoriteDto, new Favorite());

        favorite.setCreatedAt(LocalDateTime.now());

        favoriteRepository.save(favorite);
    }

    public void flush(){
        favoriteRepository.flush();
    }

    public User persist(UserLodgingId dto) {
        try {
            Favorite favorite = findById(dto);
            delete(favorite.getId());
            return favorite.getUser();
        } catch (Exception ignore) {
            return create(dto).getUser();
        }
    }

    public Favorite dtoToObject(UserLodgingId favoriteDto, Favorite favorite) {

        favorite.setUser(userService.findById(favoriteDto.getUserUuid()));
        favorite.setLodging(lodgingService.findById(favoriteDto.getLodgingUuid()));
        favorite.setId(favoriteDto);

        return favorite;
    }

    public void delete(UserLodgingId favorite) {
        favoriteRepository.deleteById(favorite);
    }

    public Favorite findById(UserLodgingId userLodgingId) throws EntityNotFoundException {
        return favoriteRepository.findById(userLodgingId).orElseThrow(() -> new EntityNotFoundException("Favourite"));
    }

    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    public long count() {
        return favoriteRepository.count();
    }
}
