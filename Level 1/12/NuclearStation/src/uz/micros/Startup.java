package uz.micros;

import uz.micros.devices.*;
import uz.micros.input.InputKeyListener;

import java.util.ArrayList;

public class Startup implements Notifier {
    private ArrayList<Device> devs;
    private InputKeyListener listener;

    public void run() {
        init();

        int key = 1;

        do{
            key = Input.getNumber();

            switch (key){
                case 1:
                    start();
                    break;
                case 2:
                    stop();
                    break;
            }

        }while(key != 0);
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
