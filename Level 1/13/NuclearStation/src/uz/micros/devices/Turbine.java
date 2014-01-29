package uz.micros.devices;

import uz.micros.Input;
import uz.micros.Notifier;
import uz.micros.devices.Device;

public class Turbine implements Device {

    private final String sort;
    private final Notifier notifer;

    public Turbine(String s, Notifier ntf) {
        notifer = ntf;
        sort = s;
    }

    public void start() {
        notifer.send(String.format("Turbine '%s' started", sort));
    }

    public void stop() {
        notifer.send(String.format("Turbine '%s' stopped", sort));
    }
}
