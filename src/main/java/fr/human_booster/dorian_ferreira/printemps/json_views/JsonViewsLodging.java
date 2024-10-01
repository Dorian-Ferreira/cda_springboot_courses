package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViewsLodging {
    public interface Address { }
    public interface RoomTypes { }
    public interface Medias { }
    public interface Bookings { }
    public interface Favorites { }
    public interface Reviews { }
    public interface Slug { }
    public interface Description { }
    public interface NightPrice { }
    public interface Accessible { }
    public interface Capacity { }
    public interface Name { }
    public interface Uuid { }
    public interface Open { }

    public interface LodgingUserShow extends
            Name,
            Slug,
            Description,
            Accessible,
            NightPrice,
            Capacity,
            Address
    { }

    public interface LodgingList extends
            JsonViewsLodging.Name,
            JsonViewsLodging.Slug,
            JsonViewsLodging.Capacity,
            JsonViewsLodging.Accessible,
            JsonViewsLodging.NightPrice
    { }

    public interface LodgingShow extends
            JsonViewsLodging.Uuid,
            JsonViewsLodging.Name,
            JsonViewsLodging.Slug,
            JsonViewsLodging.Capacity,
            JsonViewsLodging.Accessible,
            JsonViewsLodging.NightPrice,
            JsonViewsLodging.Description,

            JsonViewsLodging.Address,
            JsonViewsAddress.AddressShow,

            JsonViewsLodging.RoomTypes,
            JsonViewsRoomType.RoomTypeShow,

            JsonViewsLodging.Medias,
            JsonViewsMedia.MediaLodgingShow,

            JsonViewsLodging.Reviews,
            JsonViewsReview.ReviewLodgingShow
    { }

    public interface LodgingSimpleShow extends
            JsonViewsLodging.Name,
            JsonViewsLodging.Slug
    { }
}
