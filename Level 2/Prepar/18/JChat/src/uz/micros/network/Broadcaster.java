package uz.micros.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Broadcaster {

    public void send(String msg, InetAddress addr, int port){
        byte[] data = new byte[1024];

        data = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, addr, port);

        try {
            DatagramSocket serverSocket = new DatagramSocket(65531);
            serverSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
