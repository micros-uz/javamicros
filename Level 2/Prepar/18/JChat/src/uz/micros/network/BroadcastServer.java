package uz.micros.network;

import java.io.IOException;
import java.net.*;

public class BroadcastServer {

    private final ConnectorSink sink;

    public BroadcastServer(ConnectorSink sink) {
        this.sink = sink;
    }

    private void waitForNewChats() {
        try {
            MulticastSocket socket = new MulticastSocket(65531);
//            socket.joinGroup();
            byte[] receiveData = new byte[1024];

            System.out.println("Broadcast Server Started");

            while (true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                InetAddress addr = packet.getAddress();
                int port = packet.getPort();

                sink.connected(msg, addr, port);
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
