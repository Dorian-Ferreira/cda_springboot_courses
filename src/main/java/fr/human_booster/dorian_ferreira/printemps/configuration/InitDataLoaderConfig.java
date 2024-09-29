package fr.human_booster.dorian_ferreira.printemps.configuration;

import fr.human_booster.dorian_ferreira.printemps.dto.*;
import fr.human_booster.dorian_ferreira.printemps.entity.embeddedId.UserLodgingId;
import fr.human_booster.dorian_ferreira.printemps.entity.Lodging;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.service.*;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Locale;

@Configuration
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private static final int NB_USERS = 200;
    private static final int NB_LODGING = 200;
    private static final int NB_FAVORITE = 500;
    private static final int NB_BOOKING = 1000;
    private static final int NB_REVIEW = 2000;

    private final LodgingService lodgingService;
    private final RoomTypeService roomTypeService;
    private final UserService userService;
    private final FavoriteService favoriteService;
    private final BookingService bookingService;
    private final ReviewService reviewService;
    private final AddressService addressService;

    private static final Faker faker = new Faker(Locale.FRANCE);

    @Override
    public void run(String... args) {

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

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            UserCreateDTO userCreateDto = new UserCreateDTO();
            userCreateDto.setPassword("12345");
            userCreateDto.setConfirmPassword("12345");
            userCreateDto.setEmail(faker.internet().emailAddress(firstName.toLowerCase() + "." + lastName.toLowerCase()));


            UserUpdateDTO userUpdateDto = new UserUpdateDTO();
            userUpdateDto.setBirthAt(faker.timeAndDate().birthday(18, 65));
            userUpdateDto.setFirstName(firstName);
            userUpdateDto.setLastName(lastName);
            userUpdateDto.setPhone(faker.phoneNumber().phoneNumber());

            User user = userService.createInit(userCreateDto, userUpdateDto);

            AddressUserDTO addressUserDTO = new AddressUserDTO();

            addressUserDTO.setCity(faker.address().city());
            addressUserDTO.setNumber(faker.address().streetAddressNumber());
            addressUserDTO.setStreet(faker.address().streetName());
            addressUserDTO.setCountry("France");
            addressUserDTO.setZipCode(faker.address().zipCode());
            addressUserDTO.setIsBilled(true);

            addressService.create(addressUserDTO, user.getUuid());
        }

        userService.flush();
    }

    private void createLodging() {
        while (lodgingService.count() < NB_LODGING) {
            AddressLodgingDTO addressLodgingDTO = new AddressLodgingDTO();

            addressLodgingDTO.setCity(faker.address().city());
            addressLodgingDTO.setNumber(faker.address().streetAddressNumber());
            addressLodgingDTO.setStreet(faker.address().streetName());
            addressLodgingDTO.setCountry("France");
            addressLodgingDTO.setZipCode(faker.address().zipCode());
            addressLodgingDTO.setLatitude(10f);
            addressLodgingDTO.setLongitude(10f);

            LodgingCreateDTO lodgingCreateDTO = new LodgingCreateDTO();

            lodgingCreateDTO.setAddressDTO(addressLodgingDTO);

            switch ((int) (Math.random() * 5)) {
                case 0:
                    lodgingCreateDTO.setName(faker.funnyName().name());
                    break;
                case 1:
                    lodgingCreateDTO.setName(faker.beer().name());
                    break;
                case 2:
                    lodgingCreateDTO.setName(faker.animal().name());
                    break;
                case 3:
                    lodgingCreateDTO.setName(faker.ancient().god());
                    break;
                case 4:
                    lodgingCreateDTO.setName(faker.harryPotter().character());
                    break;
            }

            lodgingCreateDTO.setIsAccessible(Math.random()>0.8f);
            lodgingCreateDTO.setCapacity(faker.number().numberBetween(5, 30));

            switch ((int) Math.floor(Math.random() * 5)) {
                case 0:
                    lodgingCreateDTO.setDescription(faker.joke().knockKnock());
                    break;
                case 1:
                    lodgingCreateDTO.setDescription(faker.joke().pun());
                    break;
                case 2:
                    lodgingCreateDTO.setDescription(faker.chuckNorris().fact());
                    break;
                case 3:
                    lodgingCreateDTO.setDescription(faker.yoda().quote());
                    break;
                case 4:
                    lodgingCreateDTO.setDescription(faker.kaamelott().quote());
                    break;
            }

            lodgingCreateDTO.setNightPrice(faker.number().numberBetween(3000, 30000));

            Lodging lodging = lodgingService.create(lodgingCreateDTO);

            for (int j = 0; j <= Math.random()*2; j++) {
                lodgingService.addRoomType(lodging.getUuid(), roomTypeService.getOneRandom());
            }
        }
        lodgingService.flush();
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
        while (favoriteService.count() < NB_FAVORITE) {
            UserLodgingId userLodgingId = new UserLodgingId(lodgingService.getOneRandom().getUuid(), userService.getOneRandom().getUuid());
            favoriteService.createInit(userLodgingId);
        }
        favoriteService.flush();
    }

    private void createBooking() {
        while (bookingService.count() > NB_BOOKING) {
            String lodging = lodgingService.getOneRandom().getUuid();
            String user = userService.getOneRandom().getUuid();

            BookingDTO bookingDTO = new BookingDTO();

            long randomYear = (long)(Math.random()*50);

            bookingDTO.setStartedAt(LocalDateTime.now().minusYears(randomYear).minusDays(2));
            bookingDTO.setFinishedAt(LocalDateTime.now().minusYears(randomYear).plusDays(4));
            bookingDTO.setQuantity((int) (Math.random()*8)+1);
            bookingDTO.setUserUuid(user);
            bookingDTO.setLodgingUuid(lodging);

            bookingService.createInit(bookingDTO);
        }

        bookingService.flush();
    }

    private void createReview() {
        while (reviewService.count() < NB_REVIEW) {
            String lodging = lodgingService.getOneRandom().getUuid();
            String user = userService.getOneRandom().getUuid();

            ReviewDTO reviewDTO = new ReviewDTO();

            switch ((int) (Math.random() * 5)) {
                case 0:
                    reviewDTO.setContent(faker.joke().knockKnock());
                    break;
                case 1:
                    reviewDTO.setContent(faker.joke().pun());
                    break;
                case 2:
                    reviewDTO.setContent(faker.chuckNorris().fact());
                    break;
                case 3:
                    reviewDTO.setContent(faker.yoda().quote());
                    break;
                case 4:
                    reviewDTO.setContent(faker.kaamelott().quote());
                    break;
            }

            reviewDTO.setRating((float) (Math.random()*5));
            reviewDTO.setLodgingUuid(lodging);
            reviewDTO.setUserUuid(user);

            reviewService.createInit(reviewDTO);
        }

        reviewService.flush();
    }
}
