package uz.micros.devices;

import uz.micros.Notifier;

public class Sensor extends BaseDevice implements Device {

    public Sensor(Notifier ntf)  {
        super(ntf);
    }

    public void start() {
        getNotifier().send(String.format("%s - started", getName()));
    }

    public void stop() {
        getNotifier().send(String.format("%s - stopped", getName()));
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
