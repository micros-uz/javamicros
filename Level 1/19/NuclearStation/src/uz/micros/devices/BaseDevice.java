package uz.micros.devices;

import uz.micros.Notifier;

public abstract class BaseDevice implements Device {

    private static int counter;
    private final int id;
    private Notifier notifier;
    private boolean started;

    public BaseDevice(Notifier ntf) {
        notifier = ntf;
        id = ++counter;
    }

    public void f(){

    }

    public Notifier getNotifier() {
        return notifier;
    }

    public int getId() {
        return id;
    }

    protected String getName() {
        return String.format("Dev: %s #%d", getClass().getSimpleName(), getId());
    }

    public void setState(boolean state){
        started = state;
    }

    public final void start() {
        if (!started) {
            if (startCore()){
                setState(true);
                showState();
            }
        }
    }

    protected boolean startCore() {
        return true;
    }

    public final void stop() {
        if (this.started) {
            this.started = false;
            stopCore();
            showState();
        }
    }

    protected void stopCore(){

    }

    public void showState(){
        String state = started ? "started" : "stopped";
        getNotifier().send(String.format("%s - %s", getName(), state));
    }
}
