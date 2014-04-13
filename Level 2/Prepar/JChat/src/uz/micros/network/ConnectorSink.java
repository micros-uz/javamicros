package uz.micros.network;

import java.net.InetAddress;

public interface ConnectorSink {
    void packetKeldi(String msg, InetAddress addr, int port);
}
