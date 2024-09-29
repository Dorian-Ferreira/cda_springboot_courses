package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViewsReview {
    public interface Lodging { }
    public interface User { }
    public interface CreatedAt { }
    public interface Rating { }
    public interface Content { }
    public interface Id { }
    public interface UpdatedAt { }
    public interface DeletedAt { }

    public interface ReviewUserShow extends
            Lodging,
            Rating,
            Content,
            CreatedAt,
            UpdatedAt
    {}

}
