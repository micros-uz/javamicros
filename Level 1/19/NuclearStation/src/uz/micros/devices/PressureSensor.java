package uz.micros.devices;

import uz.micros.Notifier;

public class PressureSensor extends Sensor {

    public PressureSensor(Notifier ntf) {
        super(ntf);

        interval = 4000;
    }
}
