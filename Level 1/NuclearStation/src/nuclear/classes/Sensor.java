/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nuclear.classes;

import input.OutputConsole;

/**
 *
 * @author artem
 */
public class Sensor implements Device{
    private final String _name;
    private final OutputConsole _console;

    public Sensor(String name, OutputConsole console){
        _name = name;
        _console = console;
    }
    
    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void start() {
        _console.println("ww");
        _console.println(String.format("%s %s", _name, "started"));
    }

    @Override
    public void stop() {
       _console.println(String.format("%s %s", _name, "stopped")); 
    }
    
}
