package uz.micros.devices;

import uz.micros.Input;
import uz.micros.Notifier;

public class Sensor implements Device {
    private final Notifier notifier;

    public Sensor(Notifier ntf) {
        notifier = ntf;
    }

    public void start() {
        notifier.send(String.format("Sensor '%s' started", "type"));
    }

    public void stop() {
        notifier.send(String.format("Sensor '%s' stopped", "type"));
    }
}
