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

    public interface LodgingUserShow extends
            Uuid,
            Name,
            Slug,
            Description,
            Accessible,
            NightPrice,
            Capacity,
            Address
    {}
}
