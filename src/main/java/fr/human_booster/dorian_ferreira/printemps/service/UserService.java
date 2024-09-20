package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.repository.UserRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements ServiceDtoInterface<User, UserCreateDTO> {
    private UserRepository userRepository;

    public User create(UserCreateDTO userDto) {
        User user = dtoToObject(userDto, new User());

        return userRepository.saveAndFlush(user);
    }

    public User update(UserUpdateDTO userDto, String uuid) {
        User user = dtoUpdateToObject(userDto, findById(uuid));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User dtoToObject(UserCreateDTO userCreateDTO, User user) {
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());

        user.setRoles("[ROLE_USER]");
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

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public long count() {
        return userRepository.count();
    }
}
