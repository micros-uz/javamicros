package uz.micros;

import uz.micros.devices.Device;

public class Turbine implements Device {

    private final String sort;

    public Turbine(String s) {
        sort = s;
    }

    public void start() {
        Input.print(String.format("Turbine '%s' started", sort));
    }

    public void stop() {
        Input.print(String.format("Turbine '%s' started", sort));
    }
}
