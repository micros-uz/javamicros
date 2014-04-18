package uz.micros.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;
    private final ServerSink sink;

    public Server(int port, ServerSink sink) {
        this.port = port;
        this.sink = sink;
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                startInternal();
            }
        }).start();
    }

    private void startInternal() {

        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Waiting for connections...");

            while (true) {
                Socket sock = socket.accept();

                sink.newConnection(sock);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
