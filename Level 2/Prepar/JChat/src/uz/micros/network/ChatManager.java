package uz.micros.network;

import java.net.Socket;

public class ChatManager implements ServerSink, ClientSink {

    public void start() {
        UdpServer udpServer = new UdpServer();
        udpServer.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connected(Socket s) {
        Thread t = new Thread(new Client(s, this));
        t.start();
    }

    @Override
    public void event(String addr, String event) {

    }


}
