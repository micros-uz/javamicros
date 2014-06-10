package uz.micros;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class Reader {
    public String Read(String viewName) {
        return "<h2>${x}</h2>";
    }
}
