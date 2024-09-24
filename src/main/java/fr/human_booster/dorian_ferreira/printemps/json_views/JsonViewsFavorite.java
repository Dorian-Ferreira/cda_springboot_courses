package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViewsFavorite {
    public interface Lodging { }
    public interface User { }
    public interface CreatedAt { }
    public interface Id { }

    public interface FavoriteUserShow extends
            Lodging,
            CreatedAt
    {}
}
