package uz.micros.network;

import java.net.Socket;

public interface ServerSink {
    void connected(Socket s);
}
