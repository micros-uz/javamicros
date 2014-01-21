package uz.micros;

public interface Plugin {
    String getName();
    boolean validate(String data);
}
