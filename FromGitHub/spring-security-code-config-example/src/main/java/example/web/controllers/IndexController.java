package example.web.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = GET)
    public String index() {
        return "index";
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/sec", method = GET)
    public String sec() {
        return "sec";
    }
}
