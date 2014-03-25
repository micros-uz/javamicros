package uz.micros.network;

public interface ClientSink {
    void event(String addr, String event);
}
