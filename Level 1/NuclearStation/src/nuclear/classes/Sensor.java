package nuclear.classes;

import input.OutputConsole;

public class Sensor extends BaseDevice{
    private final String _type;
    private static int _id;

    public Sensor(String type, OutputConsole console){
        super(console);
        _type = type;
        
        _id++;
    }
    
    @Override
    protected String getDevName() {
        return String.format("%s Id=%d", _type, _id);
    }   
}
