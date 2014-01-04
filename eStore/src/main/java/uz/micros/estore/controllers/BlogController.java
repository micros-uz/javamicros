package uz.micros.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @RequestMapping(method = RequestMethod.GET)
    public String blogIndex(ModelMap model) {
        model.addAttribute("message", "Hi,ww eStore!");
        Date curDate =  new Date();
        model.addAttribute("curDate", curDate.toString());

        return "blogIndex";
    }
}
