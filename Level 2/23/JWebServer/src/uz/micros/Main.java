package uz.micros;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ServerCloneException;

public class Main {

    public static void main(String[] args) {

        try {
            ServerSocket srvSockect = new ServerSocket(80);

            Socket socket = srvSockect.accept();

            new HttpHandler(socket).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
