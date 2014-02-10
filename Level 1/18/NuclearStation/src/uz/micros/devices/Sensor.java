package uz.micros.devices;

import uz.micros.Notifier;

public class Sensor extends BaseDevice {
    protected int interval = 2000;
    private SensorTask task;

    public Sensor(Notifier ntf)  {
        super(ntf);
    }

    public boolean startCore(){
        SensorStartThread t = new SensorStartThread(this);
        t.start();

        return false;
    }

    public final int getInterval(){
        return interval;
    }

    protected void stopCore(){
       task.cancel();
    }

    public void register(SensorTask sensorTask) {
        task = sensorTask;
    }
}


/*

new Thread("" + i++){
            public void run(){
                System.out.println("Thread: " + getName() + " running");
                try {
                    Thread.sleep(2000);
                    System.out.println("Thread: " + getName() + " running2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

*/
