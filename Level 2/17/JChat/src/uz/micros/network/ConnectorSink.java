package uz.micros.network;

import java.net.Socket;

public interface ConnectorSink {
    void connected(Socket socket);
}
