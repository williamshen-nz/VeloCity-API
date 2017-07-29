package nz.shen.velocity.velocity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@RestController
public class RouteController {
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
        Map<String, Object> result = JSON.parse(json);
        try {
            for (Object route : (ArrayList<Object>) result.get("routes")) {
                directions.add(JSON.parse(JSON.stringify(route), DirectionsObject.class));
            }
        } catch (Exception e) {
            return JSON.stringify("{message: \"Error! Your request was not valid. Please try be more specific.\"");
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
        } else {
            Random random = new Random();
            return JSON.stringify(directions.get(random.nextInt(directions.size())));
        }
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
        System.out.println("Route: " + direction.getSummary() + ", Score: " + score);
        return score;
    }
}
