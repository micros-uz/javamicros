package uz.micros.network;

import java.net.InetAddress;
import java.net.Socket;

public interface ConnectorSink {
    void connected(Socket socket);
    void connected(String msg, InetAddress addr, int port);
}
