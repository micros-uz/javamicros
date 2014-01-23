package uz.micros.modules;

import uz.micros.Plugin;

import java.util.Date;

public class IIVModule implements Plugin {
    @Override
    public void send(String code) {
        System.out.println("Registererd: " +
                new Date() + " " + code);
    }
}
