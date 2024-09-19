package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User create(UserCreateDTO userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        user.setRoles("ROLE_USER");
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.saveAndFlush(user);
    }

    public User update(UserUpdateDTO userDto, String uuid) {
        User user = findById(uuid);

        if(!user.getPassword().equals(userDto.getPassword()))
            user.setPassword(userDto.getPassword());

        if(!user.getFirstName().equals(userDto.getFirstName()))
            user.setFirstName(userDto.getFirstName());

        if(!user.getLastName().equals(userDto.getLastName()))
            user.setLastName(userDto.getLastName());

        if(!user.getBirthAt().equals(userDto.getBirthAt()))
            user.setBirthAt(userDto.getBirthAt());

        if(!user.getPhone().equals(userDto.getPhone()))
            user.setPhone(userDto.getPhone());

        if(!user.getPhoto().equals(userDto.getPhoto()))
            user.setPhoto(userDto.getPhoto());

        return userRepository.saveAndFlush(user);
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
}
