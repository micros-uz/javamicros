package uz.micros.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Broadcaster  {
/*    private final String msg;
    private final InetAddress addr;
    private final int port;*/

    public Broadcaster() {
/*        this.msg = msg;
        this.addr = addr;
        this.port = port;*/
    }

    public void send(String msg, InetAddress addr, int port){

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendInternal(msg, addr, port);
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
}
