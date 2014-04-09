package uz.micros.network;

public interface ClientSink {
    void notifyEvent(ClientEvent clientEvent);
    String getHostName();
}
