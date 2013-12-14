package nuclearstation;

import input.Input;
import java.util.ArrayList;
import nuclear.classes.*;
import org.jnativehook.keyboard.NativeKeyEvent;

public class Startup {
    private ArrayList<Device> _devices;
    private Input _input;

    void run() {
                
        _input = new Input();
        _input.println("Nuclear station embedded firmware 1.0");
        
        int key = 0;
        
        while (key != NativeKeyEvent.VK_ESCAPE){
            key = _input.readKey();
            
            switch(key)
            {
                case NativeKeyEvent.VK_F2:
                    start();
                    break;
                case NativeKeyEvent.VK_F3:
                    stop();
                    break;
                case NativeKeyEvent.VK_UP:
                    reactorPlus();
                    break;
            }
        }    
        
        _input.println("GAME OVER");
    }    
    
    private void init() {
        _devices = new ArrayList<>();
        
        _devices.add(new Sensor("Temperature", _input));
        _devices.add(new Sensor("Voltage", _input));
        _devices.add(new Sensor("Pressure", _input));
        _devices.add(new Reactor(_input));
    }

    private void start() {

        if(_devices == null)
            init();
        
        for(Device d : _devices)
            d.start();        
    }

    private void stop() {
       for(Device d : _devices)
           d.stop();
    }    

    private void reactorPlus() {
        _input.println("fef");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
