package uz.micros.network;

import java.net.InetAddress;
import java.net.Socket;

public interface ConnectorSink {
    void packetKeldi(String msg, InetAddress addr, int port);

    void connected(Socket socket);
}
