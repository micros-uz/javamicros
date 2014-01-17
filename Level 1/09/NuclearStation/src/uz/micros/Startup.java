package uz.micros;

import uz.micros.devices.Device;
import uz.micros.devices.Sensor;

import java.util.ArrayList;

public class Startup {
    private ArrayList<Device> devs = new ArrayList<Device>();

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
        devs.add(new Sensor("Pressure"));
        devs.add(new Sensor("Voltage"));
        devs.add(new Sensor("Temperature"));

        devs.add(new Turbine("Left"));
        devs.add(new Turbine("Right"));
    }
}
