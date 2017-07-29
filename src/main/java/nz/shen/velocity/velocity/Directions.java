package nz.shen.velocity.velocity;

public class Directions {
    private final String SITE_URL = "https://maps.googleapis.com/maps/api/directions/json?";
    private final String ORIGIN_STRING = "origin=";
    private final String ORIGIN;
    private final String DESTINATION_STRING = "&destination=";
    private final String DESTINATION;
    private final String MODE_STRING = "&mode=bicycling";
    private final String KEY_STRING = "&key=";
    private final String API_KEY = "AIzaSyDiWD_CIwQlKylcSYTrxAwrEihVQB19jI4";
    private final String ALTERNATIVES = "&alternatives=true";

    public Directions(String ORIGIN, String DESTINATION) {
        this.ORIGIN = ORIGIN;
        this.DESTINATION = DESTINATION;
    }

    public String getRequestString() {
        String request = "";
        request += SITE_URL;
        request += ORIGIN_STRING;
        request += ORIGIN;
        request += DESTINATION_STRING;
        request += DESTINATION;
        request += MODE_STRING;
        request += ALTERNATIVES;
        request += KEY_STRING;
        request += API_KEY;
        return request;
    }
}