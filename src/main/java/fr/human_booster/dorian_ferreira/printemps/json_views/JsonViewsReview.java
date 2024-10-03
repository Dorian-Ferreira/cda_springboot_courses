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
            JsonViewsLodging.LodgingSimpleShow,
            Rating,
            Content,
            CreatedAt,
            UpdatedAt
    {}

    public interface ReviewLodgingShow extends
            User,
            JsonViewsUser.UserSimpleShow,
            Rating,
            Content,
            CreatedAt,
            UpdatedAt
    {}

    public interface ReviewShow extends
            User,
            JsonViewsUser.UserSimpleShow,

            Lodging,
            JsonViewsLodging.LodgingSimpleShow,

            Rating,
            Content,
            CreatedAt,
            UpdatedAt
    { }
}
