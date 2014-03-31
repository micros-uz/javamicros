package uz.micros.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(Socket sock) {
        socket = sock;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);


        } catch (IOException e) {
            e.printStackTrace();
        }

        String msg;
        try {
            while((msg = in.readLine()) != null){
                if (msg.toUpperCase().indexOf("BAD") > -1)
                    out.println("You are very bad guy!");
                else{
                    System.out.println("Client " + socket.getInetAddress().getHostAddress() + " says: " + msg);
                    out.println("OK - " + msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
