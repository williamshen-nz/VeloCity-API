package nz.shen.velocity.velocity.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by manalmohania on 29/7/17.
 */
public class NearestRoads {
    private static final String API_KEY = "AIzaSyDiWD_CIwQlKylcSYTrxAwrEihVQB19jI4";

    public static class Coordinate{
        private double x;
        private double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {return x;}
        public double getY() {return y;}

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    private Coordinate start, end;
    private Random random = new Random();

    NearestRoads(double x1, double y1, double x2, double y2) {
        this.start = new Coordinate(x1, y1);
        this.end = new Coordinate(x2, y2);
    }

    private Coordinate getCentre() {
        return new Coordinate((start.x + end.x)/2, (start.y + end.y)/2);
    }

    private List<Coordinate> getCoords() {
        int num = 10;

        Coordinate centre = getCentre();
        double radius = Math.sqrt((start.x - centre.x) * (start.x - centre.x) + (start.y - centre.y) * (start.y - centre.y));

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