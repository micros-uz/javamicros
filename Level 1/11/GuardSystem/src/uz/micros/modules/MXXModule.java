package uz.micros.modules;

import uz.micros.PluginEx;

import java.util.Date;

public class MXXModule implements PluginEx {
    @Override
    public void send(String code) {
        System.out.println("Registererd: " +
                new Date() + " " + code);
    }

    public boolean allow(String code){
        return !code.contains("x");
    }
}
