package uz.micros.devices;

import uz.micros.Notifier;

public abstract class BaseDevice implements Device {

    private static int counter;
    private final int id;
    private Notifier notifier;
    private boolean started;

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

    public void start() {
        getNotifier().send(String.format("%s - started", getName()));
    }

    public void stop() {
        getNotifier().send(String.format("%s - stopped", getName()));
    }
}
