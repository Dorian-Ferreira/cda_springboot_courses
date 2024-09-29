package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.AddressRepository;
import fr.human_booster.dorian_ferreira.printemps.repository.FavoriteRepository;
import fr.human_booster.dorian_ferreira.printemps.repository.UserRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements ServiceDtoInterface<User, UserCreateDTO> {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final AddressRepository addressRepository;

    public User create(UserCreateDTO userDto) {
        User user = dtoToObject(userDto, new User());

        return userRepository.saveAndFlush(user);
    }

    public User update(UserUpdateDTO userDto, String uuid) {
        try {
            User user = dtoUpdateToObject(userDto, findById(uuid));

            return userRepository.saveAndFlush(user);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public User createInit(UserCreateDTO createDTO, UserUpdateDTO updateDTO) {
        User user = dtoUpdateToObject(updateDTO, dtoToObject(createDTO, new User()));
        return userRepository.save(user);
    }

    public void flush() {
        userRepository.flush();
    }

    @Override
    public User dtoToObject(UserCreateDTO userCreateDTO, User user) {
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());

        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    private User dtoUpdateToObject(UserUpdateDTO userUpdateDTO, User user) {
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setBirthAt(userUpdateDTO.getBirthAt());
        user.setPhone(userUpdateDTO.getPhone());
        user.setPhoto(userUpdateDTO.getPhoto());

        return user;
    }

    public boolean delete(String id) {
        User user = findById(id);

        user.setFirstName(null);
        user.setLastName(null);
        user.setBirthAt(null);
        user.setPhone(null);
        user.setPhoto(null);
        user.setRoles("[\"DELETED_USER\"]");
        user.setEmail("Deleted User");

        user.getAddresses().forEach((address -> {
            address.setUser(null);
            addressRepository.save(address);
        }));
        favoriteRepository.deleteAll(user.getFavorites());

        userRepository.saveAndFlush(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String userId) throws EntityNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
    }

    public long count() {
        return userRepository.count();
    }

    public User getOneRandom() {
        return userRepository.findRandom();
    }
}
