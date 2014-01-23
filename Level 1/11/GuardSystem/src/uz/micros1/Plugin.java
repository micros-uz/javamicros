package uz.micros1;

public interface Plugin {
    String getName();
    boolean validate(String data);
}
