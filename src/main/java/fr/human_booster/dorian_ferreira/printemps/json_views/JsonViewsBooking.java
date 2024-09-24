package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViewsBooking {
    public interface Lodging {}
    public interface User { }
    public interface Quantity {}
    public interface FinishedAt {}
    public interface StartedAt {}
    public interface CreatedAt {}
    public interface Number {}
    public interface Uuid {}

    public interface BookingUserShow extends
            Uuid,
            Number,
            StartedAt,
            FinishedAt,
            Lodging
    {}
}
