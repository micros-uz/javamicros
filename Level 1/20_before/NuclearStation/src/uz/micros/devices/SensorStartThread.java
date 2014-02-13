package uz.micros.devices;

import java.util.Timer;
import java.util.TimerTask;

public class SensorStartThread extends Thread {

    private final Sensor s;

    public SensorStartThread(Sensor i) {
        s = i;
    }

    public void run() {
        try {
            Thread.sleep(5000);
            s.setState(true);
            s.showState();

            Timer t = new Timer();
            t.schedule(new SensorTask(s), 1000, s.getInterval());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
