package uz.micros.network;

import java.net.Socket;

public interface ServerSink {
    void newConnection(Socket sock);
}
