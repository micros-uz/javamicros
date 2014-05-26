package uz.micros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Parser {

    @Autowired
    private X x;

    public String Parse(String tmpl) {
        return tmpl.replace("${x}", "Hello!");
    }
}
