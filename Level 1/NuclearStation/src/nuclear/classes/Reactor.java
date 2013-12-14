/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nuclear.classes;

import input.OutputConsole;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author admin
 */
public class Reactor extends BaseDevice {

    public Reactor(OutputConsole console) {
        super(console);
    }

    @Override
    protected String getDevName() {
        return "VBD25";
    }
    
    public void start(){
        Timer t = new Timer();
        t.schedule(new DelayStartTask(this), 400);
    }
}
