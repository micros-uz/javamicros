package nuclear.classes;

import input.OutputConsole;

public abstract class BaseDevice implements Device{
    private final OutputConsole _console;
    
    public BaseDevice(OutputConsole console){
        _console = console;
    }
    
    public void start(){
        _console.println(getMsg("started"));
    }
    
    public void stop(){
        _console.println(getMsg("stopped")); 
    }    
    
    protected abstract String getDevName();
    
    private String getMsg(String opName){
        return String.format("%s '%s' %s", getClass().getSimpleName(), getDevName(), opName);
    }
}
