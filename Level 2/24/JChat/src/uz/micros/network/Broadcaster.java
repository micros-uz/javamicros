package uz.micros.network;

import java.io.IOException;
import java.net.*;

public class Broadcaster  {

    private final ConnectorSink sink;

    public Broadcaster(ConnectorSink sink) {
        this.sink = sink;
    }

    public void send(InetAddress addr, int port){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //sendInternal(msg, addr, port);
                connect(addr, port);
            }
        }).start();
    }

    private void sendInternal(String msg, InetAddress addr, int port) {
        byte[] data = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(data,
                data.length, addr, port);

        try {
            DatagramSocket socket = new DatagramSocket();

            socket.send(packet);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect(InetAddress addr, int port){
        Socket socket = null;

        try {
            socket = new Socket(addr, port);

            sink.connected(socket);
        } catch (IOException e) {
        } catch (SecurityException e){
        }

        if (socket == null){
            System.out.println("Connect to "
                    + addr.getHostAddress() + " failed");
        }
    }
}
