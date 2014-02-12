package uz.micros;

import org.jnativehook.keyboard.NativeKeyEvent;
import uz.micros.devices.Device;
import uz.micros.input.InputKeyListener;

import java.util.List;

public class CommandProcessor {
    private final Iterable<Device> devs;
    private InputKeyListener listener;

    public CommandProcessor(Iterable<Device> devices) {
        listener = new InputKeyListener();
        listener.init();

        devs = devices;
    }

    void run(){
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
    }

    private void stop() {
        for(Device d : devs)
            d.stop();
    }

    private void start() {
        for(Device d : devs)
            d.start();
    }
}
