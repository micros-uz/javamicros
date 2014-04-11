package uz.micros.network;

import java.io.IOException;
import java.net.*;

public class BroadcastServer {
    private void waitForNewChats() {
        try {
            MulticastSocket socket = new MulticastSocket(65531);
//            socket.joinGroup();
            byte[] receiveData = new byte[1024];

            System.out.println("Broadcast Server Started");

            while (true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                String sentence = new String(packet.getData());
                InetAddress addr = packet.getAddress();
                int port = packet.getPort();

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
