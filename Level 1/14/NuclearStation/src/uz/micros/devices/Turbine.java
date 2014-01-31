package uz.micros.devices;

import uz.micros.Input;
import uz.micros.Notifier;
import uz.micros.devices.Device;

public class Turbine extends BaseDevice implements Device {

    private final String sort;

    public Turbine(String s, Notifier ntf) {
        super(ntf);
        sort = s;
    }

    public void start() {
        getNotifier().send(String.format("%s (%s) - started", getName(), sort));
    }

    public void stop() {
        getNotifier().send(String.format("%s (%s) - stopped", getName(), sort));
    }
}
