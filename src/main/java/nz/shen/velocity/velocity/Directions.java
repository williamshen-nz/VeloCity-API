package nz.shen.velocity.velocity;

import java.util.List;

public class Directions {
    private String start;
    private String end;

    private List<Route> routes;
    private static final int ROUTE_LIMIT = 50;

    public Directions(String start, String end) {
        this.start = start;
        this.end = end;
    }


}
