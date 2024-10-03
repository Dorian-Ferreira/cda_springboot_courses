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
import java.util.ArrayList;
import java.util.List;
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
        List<String> emails = new ArrayList<>();

        long nbLoop = NB_USERS - userService.count();
        for (int i = 0; i < nbLoop; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            UserCreateDTO userCreateDto = new UserCreateDTO();
            userCreateDto.setPassword("12345");
            userCreateDto.setBirthAt(faker.timeAndDate().birthday(18, 65));
            userCreateDto.setFirstName(firstName);
            userCreateDto.setLastName(lastName);

            String emailStart = faker.internet().emailAddress(firstName.toLowerCase() + "." + lastName.toLowerCase().replaceAll(" ", "_"));
            if(emails.contains(emailStart)) {
                i--;
                continue;
            }
            userCreateDto.setEmail(emailStart);
            emails.add(emailStart);

            UserUpdateDTO userUpdateDto = new UserUpdateDTO();
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

        try {
            userService.upgradeToAdmin(userService.findByEmail(emails.getFirst()).getUuid());
        } catch (Exception ignore) {

        }
    }

    private void createLodging() {
        List<String> names = new ArrayList<>();
        long nbLoop = NB_LODGING - lodgingService.count();
        for (int i = 0; i < nbLoop; i++) {
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

            if(names.contains(lodgingCreateDTO.getName())) {
                i--;
                continue;
            }

            names.add(lodgingCreateDTO.getName());

            lodgingCreateDTO.setIsAccessible(Math.random()>0.8f);
            lodgingCreateDTO.setCapacity(faker.number().numberBetween(3, 15));

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

            for (int j = 0; j <= Math.random()*3; j++) {
                lodgingService.addRoomTypeInit(lodging.getUuid(), roomTypeService.getOneRandom());
            }
        }
        lodgingService.flush();
    }

    private void createRoom() {
        List<String> roomTypes = List.of("Kitchen","Bathroom","Master Bedroom","Living Room","Attic","Basement","Garage","Jacuzzi","Pool","1 Bed Bedroom","2 Bed Bedroom");

        if(roomTypeService.count() >= roomTypes.size())
            return;

        roomTypes.forEach((r) -> {
            RoomTypeDTO roomTypeDTO = new RoomTypeDTO();

            roomTypeDTO.setType(r);

            roomTypeService.create(roomTypeDTO);
        });
    }

    private void createFavorite() {
        while (favoriteService.count() < NB_FAVORITE) {
            UserLodgingId userLodgingId = new UserLodgingId(lodgingService.getOneRandom().getUuid(), userService.getOneRandom().getUuid());
            favoriteService.createInit(userLodgingId);
        }
        favoriteService.flush();
    }

    private void createBooking() {
        long nbLoop = NB_BOOKING - bookingService.count();
        for (int i = 0; i < nbLoop; i++) {
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
        long nbLoop = NB_REVIEW - reviewService.count();
        for (int i = 0; i < nbLoop; i++) {
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
