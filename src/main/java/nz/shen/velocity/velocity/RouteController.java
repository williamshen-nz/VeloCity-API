package nz.shen.velocity.velocity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    private static final String API_KEY = "";

    @RequestMapping(value = "getRoute", method = RequestMethod.GET)
    public String getRoute(@RequestParam String origin,
                         @RequestParam String destination,
                         @RequestParam String option) {
        // Fix query string if required
        origin = origin.replace(" ", "+");
        destination = destination.replace(" ", "+");

        // Get user request
        Option userOption = Option.valueOf(option);

        // Create directions request
        Directions directions = new Directions(origin, destination);

        String routes = Network.getRequest(directions.getRequestString());
        return routes;
    }

}
