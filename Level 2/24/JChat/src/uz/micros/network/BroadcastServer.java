package uz.micros.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BroadcastServer implements Runnable{

    private final ConnectorSink sink;

    public BroadcastServer(ConnectorSink sink) {
        this.sink = sink;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {

        try {
            MulticastSocket socket = new MulticastSocket(65531);

            System.out.println("Broadcast server started...");

            byte[] data = new byte[1024];
            while(true){
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                InetAddress addr = packet.getAddress();
                int port = packet.getPort();

                sink.packetKeldi(msg, addr, port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
