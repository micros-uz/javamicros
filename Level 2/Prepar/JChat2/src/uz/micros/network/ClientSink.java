package uz.micros.network;

public interface ClientSink {
    void event(Client client, String msg);
}
