package uz.micros.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client implements Runnable {
    private final ClientSink sink;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(Socket sock, ClientSink sink) {
        this.socket = sock;
        this.sink = sink;
    }

    public String getAddr() {
        return socket.getInetAddress().getHostName();
        //return socket.getInetAddress().getLocalHost().getHostAddress();
    }

    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println(e);
            return;
        }

        final PrintWriter out2 = out;

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                out2.println("Hi");
            }
        }, 1000, 1000);

        String msg;
        try {
            while ((msg = in.readLine()) != null) {
                System.out.println("Client says: " + msg);

                sink.event(this, msg);

                out.println("OK");
            }

            out.println("GAME OVER");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
