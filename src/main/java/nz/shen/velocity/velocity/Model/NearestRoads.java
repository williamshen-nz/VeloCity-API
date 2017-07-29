package nz.shen.velocity.velocity.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by manalmohania on 29/7/17.
 */
public class NearestRoads {
    private static final String API_KEY = "AIzaSyDiWD_CIwQlKylcSYTrxAwrEihVQB19jI4";

    static class Coordinate {
        double x;
        double y;

        Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    private Coordinate left, right;
    private Random random = new Random();

    NearestRoads(double x1, double y1, double x2, double y2) {
        this.left = new Coordinate(x1, y1);
        this.right = new Coordinate(x2, y2);
    }

    private Coordinate getCentre() {
        return new Coordinate((left.x + right.x) / 2, (left.y + right.y) / 2);
    }

    private List<Coordinate> getCoords() {
        int num = 10;

        Coordinate centre = getCentre();
        double radius = Math.sqrt((left.x - centre.x) * (left.x - centre.x) + (left.y - centre.y) * (left.y - centre.y));

        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            coordinates.add(new Coordinate(centre.x + random.nextDouble() * 2 * radius - radius, centre.y + random.nextDouble() * 2 * radius - radius));
        }

        return coordinates;
    }

    // DO NOT FORGET TO ADD &key=YOUR_API_KEY at the end
    public String getQueryURL() {
        List<Coordinate> coordinates = getCoords();

        boolean first = true;
        String ret = "https://roads.googleapis.com/v1/nearestRoads?points=";

        for (Coordinate coordinate : coordinates) {
            if (!first) {
                ret += "|";
            }
            first = false;
            ret += coordinate;
        }
        return ret + "&key=" + API_KEY;
    }
}