package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViews {

    public interface CustomResponse {}

    public interface UserShow extends
            JsonViewsUser.UserShow,
            CustomResponse
    {}

    public interface LodgingList extends
            JsonViewsLodging.LodgingList,
            CustomResponse
    { }

    public interface LodgingShow extends
            JsonViewsLodging.LodgingShow,
            CustomResponse
    { }

    public interface RoomTypeShow extends
            JsonViewsRoomType.RoomTypeShow,
            CustomResponse
    { }

    public interface ReviewSimpleShow extends
            JsonViewsReview.ReviewShow,
            CustomResponse
    { }

    public interface BookingShow extends
            JsonViewsBooking.BookingShow,
            CustomResponse
    { }
}
