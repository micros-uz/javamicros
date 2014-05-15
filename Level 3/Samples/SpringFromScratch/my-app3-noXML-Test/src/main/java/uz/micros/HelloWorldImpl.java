package uz.micros;

public class HelloWorldImpl implements HelloWorld {

    @Override
    public String printHelloWorld(String name) {

        return "Hello: " + name;
    }

}