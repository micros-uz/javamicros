package uz.micros.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BroadcastServer {
    private void waitForNewChats() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(65531);
            byte[] receiveData = new byte[1024];

            System.out.println("Broadcast Server Started");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                InetAddress addr = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("RECEIVED: " + sentence + " from " +
                        addr.getHostAddress() + ":" + port);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitForNewChats();
            }
        }).start();
    }
}
