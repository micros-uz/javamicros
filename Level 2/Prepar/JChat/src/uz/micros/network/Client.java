package uz.micros.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private final ClientSink sink;
    private Socket client;

    public Client(Socket client, ClientSink sink) {
        this.client = client;
        this.sink = sink;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {

            in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));

            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println(e);
            return;
        }

        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                System.out.println("Client says: " + msg);
                out.println("OK");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
