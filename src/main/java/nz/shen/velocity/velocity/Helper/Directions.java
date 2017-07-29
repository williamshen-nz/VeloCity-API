package nz.shen.velocity.velocity.Helper;

public class Directions {
    private static final String SITE_URL = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String ORIGIN_STRING = "origin=";
    private static final String DESTINATION_STRING = "&destination=";
    private static final String MODE_STRING = "&mode=bicycling";
    private static final String KEY_STRING = "&key=";
    private static final String API_KEY = "AIzaSyDiWD_CIwQlKylcSYTrxAwrEihVQB19jI4";
    private static final String ALTERNATIVES = "&alternatives=true";

    public static String getRequestString(String ORIGIN, String DESTINATION) {
        return SITE_URL + ORIGIN_STRING + ORIGIN + DESTINATION_STRING + DESTINATION +
                MODE_STRING + ALTERNATIVES + KEY_STRING + API_KEY;
    }
}