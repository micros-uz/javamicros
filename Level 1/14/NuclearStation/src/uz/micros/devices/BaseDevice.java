package uz.micros.devices;

import uz.micros.Notifier;

public abstract class BaseDevice {

    private static int counter;

    private final int id;

    private Notifier notifier;

    public BaseDevice(Notifier ntf) {
        notifier = ntf;
        id  = ++counter;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public int getId() {
        return id;
    }

    protected String getName(){
        return String.format("Dev: %s #%d", getClass().getSimpleName(), getId());
    }
}
