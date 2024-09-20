package fr.human_booster.dorian_ferreira.printemps.configuration;

import fr.human_booster.dorian_ferreira.printemps.dto.*;
import fr.human_booster.dorian_ferreira.printemps.entity.FavoriteId;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.service.*;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final int NB_USERS = 200;
    private static final int NB_LODGING = 50;
    private static final int NB_FAVORITE = 80;
    private static final int NB_BOOKING = 80;
    private static final int NB_REVIEW = 300;

    private final LodgingService lodgingService;
    private final RoomTypeService roomTypeService;
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final BookingService bookingService;
    private final ReviewService reviewService;

    private static final Faker faker = new Faker(Locale.FRANCE);

    @Override
    public void run(String... args) throws Exception {

        createUsers();
        createRoom();
        createLodging();
        createFavorite();
        createBooking();
        createReview();

    }

    private void createUsers() {
        if(userService.count() >= NB_USERS)
            return;

        for (int i = 0; i < NB_USERS; i++) {
            UserCreateDTO userCreateDto = new UserCreateDTO();
            userCreateDto.setPassword("12345");
            userCreateDto.setConfirmPassword("12345");
            userCreateDto.setEmail(faker.internet().emailAddress());

            User user = userService.create(userCreateDto);

            UserUpdateDTO userUpdateDto = new UserUpdateDTO();
            userUpdateDto.setBirthAt(LocalDate.now().minusYears((long)(Math.random()*50)));
            userUpdateDto.setFirstName(faker.name().firstName());
            userUpdateDto.setLastName(faker.name().lastName());
            userUpdateDto.setPhone(faker.phoneNumber().phoneNumber());

            userService.update(userUpdateDto, user.getUuid());
        }
    }

    private void createLodging() {
        if(lodgingService.count() >= NB_LODGING)
            return;

        for (int i = 0; i < NB_LODGING; i++) {
            AddressLodgingDTO addressLodgingDTO = new AddressLodgingDTO();

            addressLodgingDTO.setCity(faker.address().city());
            addressLodgingDTO.setNumber(faker.address().streetAddressNumber());
            addressLodgingDTO.setStreet(faker.address().streetName());
            addressLodgingDTO.setCountry(faker.address().country());
            addressLodgingDTO.setZipCode(faker.address().zipCode());
            addressLodgingDTO.setLatitude(10f);
            addressLodgingDTO.setLongitude(10f);

            LodgingCreateDTO lodgingCreateDTO = new LodgingCreateDTO();

            lodgingCreateDTO.setAddressDTO(addressLodgingDTO);
            lodgingCreateDTO.setName(faker.cat().name());
            lodgingCreateDTO.setAccessible(true);
            lodgingCreateDTO.setCapacity(42);
            lodgingCreateDTO.setDescription(faker.cat().name());
            lodgingCreateDTO.setNightPrice(4222);

            Lodging lodging = lodgingService.create(lodgingCreateDTO);

            for (int j = 0; j <= Math.random()*2; j++) {
                lodgingService.addRoomType(lodging.getUuid(), roomTypeService.getOneRandom());
            }
        }
    }

    private void createRoom() {
        if(roomTypeService.count() >= 4)
            return;

        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();

        roomTypeDTO.setTranslationKey("jaccuzi");
        roomTypeDTO.setType("oui");

        roomTypeService.create(roomTypeDTO);

        RoomTypeDTO roomTypeDTO2 = new RoomTypeDTO();

        roomTypeDTO2.setTranslationKey("pas un jaccuzi");
        roomTypeDTO2.setType("oui");

        roomTypeService.create(roomTypeDTO2);

        RoomTypeDTO roomTypeDTO3 = new RoomTypeDTO();

        roomTypeDTO3.setTranslationKey("peut etre un jaccuzi");
        roomTypeDTO3.setType("oui");

        roomTypeService.create(roomTypeDTO3);

        RoomTypeDTO roomTypeDTO4 = new RoomTypeDTO();

        roomTypeDTO4.setTranslationKey("definitivement un jaccuzi");
        roomTypeDTO4.setType("oui");

        roomTypeService.create(roomTypeDTO4);
    }

    private void createFavorite() {
        if(favoriteService.count() >= NB_FAVORITE)
            return;

        for (int i = 0; i < NB_FAVORITE; i++) {
            FavoriteId favoriteId = new FavoriteId(lodgingService.getOneRandom().getUuid(), userService.getOneRandom().getUuid());
            favoriteService.create(favoriteId);
        }
    }

    private void createBooking() {
        if(bookingService.count() >= NB_BOOKING)
            return;

        for (int i = 0; i < NB_BOOKING; i++) {
            String lodging = lodgingService.getOneRandom().getUuid();
            String user = userService.getOneRandom().getUuid();

            BookingDTO bookingDTO = new BookingDTO();

            long randomYear = (long)(Math.random()*50);

            bookingDTO.setStartedAt(LocalDateTime.now().minusYears(randomYear).minusDays(2));
            bookingDTO.setFinishedAt(LocalDateTime.now().minusYears(randomYear).plusDays(4));
            bookingDTO.setQuantity((int) (Math.random()*8)+1);
            bookingDTO.setUserUuid(user);
            bookingDTO.setLodgingUuid(lodging);

            bookingService.create(bookingDTO);
        }
    }

    private void createReview() {
        if(reviewService.count() >= NB_REVIEW)
            return;

        for (int i = 0; i < NB_REVIEW; i++) {
            String lodging = lodgingService.getOneRandom().getUuid();
            String user = userService.getOneRandom().getUuid();

            ReviewDTO reviewDTO = new ReviewDTO();

            reviewDTO.setContent(faker.joke().knockKnock());

            reviewDTO.setRating((float) (Math.random()*5));
            reviewDTO.setLodgingUuid(lodging);
            reviewDTO.setUserUuid(user);

            reviewService.create(reviewDTO);
        }
    }
}
