package fr.human_booster.dorian_ferreira.printemps.route;

public class UrlRoute {

    private final static String BASE_API = "/api";

    public final static String CREATE = "/create";
    public final static String EDIT = "/edit";
    public final static String DELETE = "/delete";

    public final static String LOGIN = BASE_API + "/login";
    public final static String REGISTER = BASE_API + "/register";

    public final static String BASE_USER = BASE_API + "/user";
    public final static String BASE_BOOKING = BASE_API + "/booking";
    public final static String BASE_LODGING = BASE_API + "/lodging";
    public final static String BASE_REVIEW = BASE_API + "/review";
    public final static String BASE_ROOM_TYPE = BASE_API + "/room_type";

    public final static String USER_ACTIVATION = BASE_USER + "/activation";
    public final static String USER_EDIT = BASE_USER + EDIT;
    public final static String USER_DELETE = BASE_USER + DELETE;
    public final static String USER_FAVORITE = BASE_USER + "/favorite";

    public final static String BOOKING_CREATE = BASE_BOOKING + CREATE;
    public final static String BOOKING_CANCEL = BASE_BOOKING + "/cancel";

    public final static String ROOM_TYPE_CREATE = BASE_ROOM_TYPE + CREATE;

    public final static String LODGING_CREATE = BASE_LODGING + CREATE;
    public final static String LODGING_EDIT = BASE_LODGING + EDIT;
    public final static String LODGING_DELETE = BASE_LODGING + DELETE;

    public final static String REVIEW_CREATE = BASE_REVIEW + CREATE;
    public final static String REVIEW_EDIT = BASE_REVIEW + EDIT;
    public final static String REVIEW_DELETE = BASE_REVIEW + DELETE;

}
