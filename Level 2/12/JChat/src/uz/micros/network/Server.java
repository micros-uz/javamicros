package uz.micros.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;

    public Server(int port){
        this.port = port;
    }

    public void start(){

        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Waiting for connections...");

            Socket client = socket.accept();
            System.out.println("Client accepted");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
