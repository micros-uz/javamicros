package uz.micros.devices;

import uz.micros.Input;
import uz.micros.Notifier;
import uz.micros.devices.Device;

public class Turbine extends BaseDevice {

    private final String sort;

    public Turbine(String s, Notifier ntf) {
        super(ntf);
        sort = s;
    }
}
