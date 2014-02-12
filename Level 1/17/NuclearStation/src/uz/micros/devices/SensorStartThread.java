package uz.micros.devices;

public class SensorStartThread extends Thread {

    private final Sensor s;

    public SensorStartThread(Sensor i) {
        s = i;
    }

    public void run(){
        try {
            Thread.sleep(5000);
            s.setState(true);
            s.showState();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
