package uz.micros.devices;

import java.util.Date;
import java.util.Random;
import java.util.TimerTask;

public class SensorTask extends TimerTask {

    private final Sensor sensor;
    private Random r;

    public SensorTask(Sensor s) {
        sensor = s;
        sensor.register(this);

        r = new Random(234782364);
    }

    @Override
    public void run() {
        System.out.println(sensor.getName() +
                " - " + r.nextInt(1000));
    }
}
