package uz.micros.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Broadcaster {

    public void send(String msg, InetAddress addr, int port){
        byte[] data = new byte[1024];

        data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);

        try {
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
