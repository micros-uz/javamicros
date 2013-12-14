package nuclear.classes;

import java.util.TimerTask;

class DelayStartTask extends TimerTask{
    public final Device _device;
    
    public DelayStartTask(Device device){
        _device = device;
    }    
    
    @Override
    public void run() 
    {
        System.out.println("e22e");
        //_device.start();
    }    
}
