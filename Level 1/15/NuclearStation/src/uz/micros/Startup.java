package uz.micros;

import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import uz.micros.devices.*;
import uz.micros.input.InputKeyListener;

import java.util.ArrayList;

public class Startup implements Notifier {
    private ArrayList<BaseDevice> devs;
    private InputKeyListener listener;

    public void run() throws InterruptedException {
        init();

        do{
            switch (listener.key){
                case NativeKeyEvent.VK_F11:
                    start();
                    listener.key = 0;
                    break;
                case NativeKeyEvent.VK_F12:
                    stop();
                    listener.key = 0;
                    break;
            }
        }while(listener.key != NativeKeyEvent.VK_ESCAPE);

        System.out.println("Exit!");

        System.exit(0);
    }

    private void stop() {
        for(Device d : devs)
            d.stop();
    }

    private void start() {
        for(Device d : devs)
            d.start();
    }

    private void init() {
        devs = new ArrayList<BaseDevice>();
        listener = new InputKeyListener();
        listener.init();

        devs.add(new PressureSensor(getNotifier()));
        devs.add(new VoltageSensor(getNotifier()));
        devs.add(new TemperatureSensor(getNotifier()));

        devs.add(new Turbine("Left", getNotifier()));
        devs.add(new Turbine("Right", getNotifier()));
    }

    private Notifier getNotifier() {
        return this;
    }

    @Override
    public void send(String s) {
        Input.print(s);
    }
}
