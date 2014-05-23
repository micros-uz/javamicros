package uz.micros;

import org.springframework.stereotype.Service;

@Service
public class Parser {
    public String Parse(String tmpl) {
        return tmpl.replace("${x}", "Hello!");
    }
}
