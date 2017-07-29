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

        Option userOption = Option.valueOf(option);
        System.out.println(origin);
        System.out.println(destination);
        System.out.println(option);

        return JSON.stringify("HEY BRO");
    }

}
