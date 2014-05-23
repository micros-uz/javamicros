package uz.micros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Reader {

    @Autowired
    Parser p;

    public String Read(String viewName) {
        return "<h2>${x}</h2>";
    }
}
