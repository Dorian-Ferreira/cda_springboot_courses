package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViews {

    public interface UserShow extends
        JsonViewsUser.UserShow,
        JsonViewsAddress.AddressUserShow,
        JsonViewsBooking.BookingUserShow,
        JsonViewsLodging.LodgingUserShow,
        JsonViewsFavorite.FavoriteUserShow,
        JsonViewsReview.ReviewUserShow,
        CustomResponse
    {}

    public interface CustomResponse {}
}
