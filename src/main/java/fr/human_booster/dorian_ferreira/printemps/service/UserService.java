package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.UserCreateDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.UserUpdateDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.exception.AlreadyActiveException;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.exception.ExpiredCodeException;
import fr.human_booster.dorian_ferreira.printemps.exception.RegisterException;
import fr.human_booster.dorian_ferreira.printemps.repository.AddressRepository;
import fr.human_booster.dorian_ferreira.printemps.repository.FavoriteRepository;
import fr.human_booster.dorian_ferreira.printemps.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final AddressRepository addressRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public User create(UserCreateDTO userDto) {
        try {

        User user = dtoToObject(userDto, new User());

        user.setActivationCode(UUID.randomUUID().toString());
        user.setActivationTimeout(LocalDateTime.now().plusMinutes(15));

        return userRepository.saveAndFlush(user);
        } catch (Exception ignored) {
            throw new RegisterException();
        }
    }

    public User update(UserUpdateDTO userDto, String uuid) {
        try {
            User user = dtoUpdateToObject(userDto, findById(uuid));

            return userRepository.saveAndFlush(user);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public User update(UserUpdateDTO userDto, Principal principal) {
        try {
            User user = dtoUpdateToObject(userDto, findByEmail(principal.getName()));

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

    public User dtoToObject(UserCreateDTO userCreateDTO, User user) {
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        user.setBirthAt(userCreateDTO.getBirthAt());
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());

        user.setRoles("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    private User dtoUpdateToObject(UserUpdateDTO userUpdateDTO, User user) {
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
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
        user.setEmail("deleted@user.com");
        user.setDeletedAt(LocalDateTime.now());

        user.setActivationCode(UUID.randomUUID().toString());

        user.getAddresses().forEach((address -> {
            address.setUser(null);
            addressRepository.save(address);
        }));
        favoriteRepository.deleteAll(user.getFavorites());
        user.setFavorites(new ArrayList<>());

        userRepository.saveAndFlush(user);

        return true;
    }

    public User upgradeToAdmin(String id) {
        User user = findById(id);

        user.setRoles("[\"ROLE_USER\",\"ROLE_ADMIN\"]");

        return userRepository.saveAndFlush(user);
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

    public User activate(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode).orElseThrow(AlreadyActiveException::new);

        if(user.getActivationTimeout().isBefore(LocalDateTime.now())) {
            throw new ExpiredCodeException();
        }

        user.setActivationCode(null);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userGrantedAuthority(user.getRoles())
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        if (role.contains("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        return authorities;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User"));
    }
}
