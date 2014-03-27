package uz.micros.network;

import java.net.Socket;

public interface ServerSink {
    void newClient(Socket socket);
}
