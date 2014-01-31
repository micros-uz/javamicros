package uz.micros;

import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import uz.micros.devices.*;
import uz.micros.input.InputKeyListener;

import java.util.ArrayList;

public class Startup implements Notifier {
    private ArrayList<Device> devs;
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
        devs = new ArrayList<Device>();
        listener = new InputKeyListener();
        listener.init();

        devs.add(new PressureSensor(this));
        devs.add(new VoltageSensor(this));
        devs.add(new TemperatureSensor(this));

        devs.add(new Turbine("Left", this));
        devs.add(new Turbine("Right", this));
    }

    @Override
    public void send(String s) {
        Input.print(s);
    }
}
