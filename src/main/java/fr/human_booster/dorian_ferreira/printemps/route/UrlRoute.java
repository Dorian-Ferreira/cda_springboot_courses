package fr.human_booster.dorian_ferreira.printemps.route;

public class UrlRoute {

    private final static String BASE_API = "/api";

    public final static String LOGIN = BASE_API + "/login";
    public final static String REGISTER = BASE_API + "/register";

    public final static String BASE_USER = BASE_API + "/user";
    public final static String BASE_BOOKING = BASE_API + "/booking";
    public final static String BASE_LODGING = BASE_API + "/lodging";
    public final static String BASE_REVIEW = BASE_API + "/review";

    public final static String CREATE = "/create";
    public final static String EDIT = "/edit";
    public final static String DELETE = "/delete";

    public final static String USER_CREATE = BASE_USER + CREATE;
    public final static String USER_EDIT = BASE_USER + EDIT;
    public final static String USER_DELETE = BASE_USER + DELETE;
    public final static String USER_FAVORITE = BASE_USER + "/favorite";
    public final static String USER_FAVORITE_ADD = USER_FAVORITE + "/edit";

}
