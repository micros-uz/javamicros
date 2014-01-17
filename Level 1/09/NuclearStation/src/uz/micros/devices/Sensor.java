package uz.micros.devices;

import uz.micros.Input;

public class Sensor implements Device {
    public final String type;

    public Sensor(String t) {
        type = t;
    }

    public String getType() {
        return type;
    }

    public void start() {
        Input.print(String.format("Sensor '%s' started", type));
    }

    public void stop() {
        Input.print(String.format("Sensor '%s' stopped", type));
    }
}
