package uz.micros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpHandler {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public HttpHandler(Socket socket) {
        this.socket = socket;

        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
        }
    }

    public void run() {
        String msg;

        try{
            msg = in.readLine();
            System.out.println(msg);
            
            while(msg != null){
                msg = in.readLine();
                System.out.println(msg);
            }
        }
        catch(IOException ex){

        }
    }
}
