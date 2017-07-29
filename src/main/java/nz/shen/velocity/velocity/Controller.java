package nz.shen.velocity.velocity;

import nz.shen.velocity.velocity.Helper.*;
import nz.shen.velocity.velocity.Model.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Controller {
    @RequestMapping(value = "getRoute", method = RequestMethod.GET)
    public String getRoute(@RequestParam String origin,
                           @RequestParam String destination,
                           @RequestParam String option) {
        // Fix query string if required
        origin = origin.replace(" ", "+");
        destination = destination.replace(" ", "+");

        // Get user request
        Option userOption = Option.valueOf(option);
        ArrayList<DirectionsObject> directions = new ArrayList<>();

        // Google Maps request, works properly
        String json = Network.getRequest(Directions.getRequestString(origin, destination));
        if (json == null || json.contains("ZERO_RESULTS")) {
            return JSON.stringify(new Message("Error! Your request was not valid." +
                    " Please try be more specific."));
        }
        System.out.println(Directions.getRequestString(origin, destination));

        Map<String, Object> result = JSON.parse(json);
        try {
            for (Object route : (ArrayList<Object>) result.get("routes")) {
                directions.add(JSON.parse(JSON.stringify(route), DirectionsObject.class));
            }
        } catch (Exception e) {
            return JSON.stringify(new Message("Error! Your request was not valid." +
                    " Please try be more specific."));
        }

        // Check user option and calculate accordingly
        if (userOption.equals(Option.DEBUG)) {
            return JSON.stringify(directions);
        } else if (userOption.equals(Option.Fastest)) {
            int min = Integer.MAX_VALUE;
            DirectionsObject best = null;
            for (DirectionsObject current : directions) {
                int allen = current.getLegs().get(0).getDistance().getValue();
                if (allen < min) {
                    min = allen;
                    best = current;
                }
            }
            return JSON.stringify(best);
        } else if (userOption.equals(Option.Shortest)) {
            int min = Integer.MAX_VALUE;
            DirectionsObject best = null;
            for (DirectionsObject current : directions) {
                int manal = current.getLegs().get(0).getDuration().getValue();
                if (manal < min) {
                    min = manal;
                    best = current;
                }
            }
            return JSON.stringify(best);
        } else if (userOption.equals(Option.Safest)) {
            int min = Integer.MAX_VALUE;
            DirectionsObject best = null;
            for (DirectionsObject current : directions) {
                int joseph = evaluate(current);
                if (joseph < min) {
                    min = joseph;
                    best = current;
                }
            }
            return JSON.stringify(best);
        } else if (userOption.equals(Option.Scenic)) {
            int min = Integer.MAX_VALUE;
            DirectionsObject best = null;
            for (DirectionsObject current : directions) {
                int tom = evaluateScenic(current);
                if (tom < min) {
                    min = tom;
                    best = current;
                }
            }
            return JSON.stringify(best);
        } else {
            Random random = new Random();
            return JSON.stringify(directions.get(random.nextInt(directions.size())));
        }
    }

    private int evaluateScenic(DirectionsObject direction) {
        int score = direction.getLegs().get(0).getDistance().getValue();
        List<NearestRoads.Coordinate> coordinates = Decoder.decode(direction.getOverviewPolyline().getPoints());

        int scenic_count = 0;
        HashMap<LongLat.Square, ScenicSpots.ScenicSpot> scenicHashmap = new HashMap<>();

        List<LongLat.Square> scenicRange = new ArrayList<>();
        for (ScenicSpots.ScenicSpot s : ScenicSpots.getScenicSpots()) {
            LongLat.Square toAdd = LongLat.getRange(s.getLongitude(), s.getLatitude(), 1500);
            scenicRange.add(toAdd);
            scenicHashmap.put(toAdd, s);
        }

        ArrayList<ScenicSpots.ScenicSpot> scenicList = new ArrayList<>();
        for (LongLat.Square s : scenicRange) {
            for (NearestRoads.Coordinate c : coordinates) {
                if (s.isInRange(c.getY(), c.getX())) {
                    scenicList.add(scenicHashmap.get(s));
                    scenic_count++;
                    break;
                }
            }
        }

        score -= scenic_count * 800;
        direction.setScenicspots(scenicList);

        return score;
    }

    private int evaluate(DirectionsObject direction) {
        // Penalise routes accordingly
        int score = direction.getLegs().get(0).getDistance().getValue();
        for (DirectionsObject.Step step : direction.getLegs().get(0).getSteps()) {
            if (step.getManeuver() == null) continue;
            if (step.getManeuver().contains("merge") || step.getManeuver().contains("uturn")) {
                score += 200;
            } else if (step.getManeuver().contains("roundabout")) {
                score += 120;
            } else if (step.getManeuver().equals("turn-right")) {
                score += 80;
            } else if (step.getManeuver().contains("right")) {
                score += 40;
            }
        }

        // Penalties for passing dangerous areas
        List<NearestRoads.Coordinate> coordinates = Decoder.decode(direction.getOverviewPolyline().getPoints());
        CycleCrashes crashes = VelocityApplication.cycleCrashes;

        int cycleCount = 0;
        HashMap<LongLat.Square, CycleCrashes.CycleCrash> cycleHashmap = new HashMap<>();

        List<LongLat.Square> crashRange = new ArrayList<>();
        for (CycleCrashes.CycleCrash cc : crashes.getCrashes()) {
            LongLat.Square toAdd = LongLat.getRange(cc.getLongitude(), cc.getLatitude(), 50);
            crashRange.add(toAdd);
            cycleHashmap.put(toAdd, cc);
        }

        ArrayList<CycleCrashes.CycleCrash> crashList = new ArrayList<>();
        for (LongLat.Square s : crashRange) {
            for (NearestRoads.Coordinate c : coordinates) {
                if (s.isInRange(c.getY(), c.getX())) {
                    crashList.add(cycleHashmap.get(s));
                    cycleCount++;
                    break;
                }
            }
        }

        score += cycleCount * 50;

        int speedingCount = 0;
        SpeedingCars speedingCars = VelocityApplication.speedingCars;

        crashRange = new ArrayList<>();

        HashMap<LongLat.Square, String> speedingHashmap = new HashMap<>();

        for (String coord : speedingCars.getDangerLevel().keySet()) {
            if (coord.equals("")) continue;
            LongLat.Square toAdd = LongLat.getRange(Double.parseDouble(coord.split(",")[0]),
                    Double.parseDouble(coord.split(",")[1]), 50);
            crashRange.add(toAdd);
            speedingHashmap.put(toAdd, coord);
        }

        ArrayList<String> speedingList = new ArrayList<>();

        for (LongLat.Square s : crashRange) {
            for (NearestRoads.Coordinate c : coordinates) {
                if (s.isInRange(c.getX(), c.getY())) {
                    speedingList.add(speedingHashmap.get(s));
                    speedingCount++;
                    break;
                }
            }
        }
        score += speedingCount * 10;

        direction.setCrashes(crashList);
        direction.setSpeeding(speedingList);

        System.out.println("Route: " + direction.getSummary() + ", Crash Zones: " + cycleCount +
                ", Speeding Zones: " + speedingCount + ", Score: " + score);
        return score;
    }


    @RequestMapping(value = "getCrashes", method = RequestMethod.GET)
    public String getCrashData() {
        return JSON.stringify(VelocityApplication.cycleCrashes);
    }
}
