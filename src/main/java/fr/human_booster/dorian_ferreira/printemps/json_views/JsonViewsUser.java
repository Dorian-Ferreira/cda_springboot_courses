package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViewsUser {
    public interface Favorites { }
    public interface Reviews { }
    public interface Addresses { }
    public interface Bookings { }
    public interface Photo { }
    public interface Phone { }
    public interface CreatedAt { }
    public interface BirthAt { }
    public interface Verified { }
    public interface Roles { }
    public interface Password { }
    public interface Email { }
    public interface LastName { }
    public interface FirstName { }
    public interface Uuid { }
    public interface IsAdmin { }
    public interface ActivationCode { }
    public interface ActivationTimeout { }

    public interface UserShow extends
            Uuid,
            FirstName,
            LastName,
            Email,
            Phone,
            BirthAt,
            CreatedAt,

            Addresses,
            JsonViewsAddress.AddressUserShow,

            Bookings,
            JsonViewsBooking.BookingUserShow,

            Favorites,
            JsonViewsFavorite.FavoriteUserShow,

            Reviews,
            JsonViewsReview.ReviewUserShow
    {}

    public interface UserSimpleShow extends
            Uuid,
            FirstName,
            LastName
    { }
}
