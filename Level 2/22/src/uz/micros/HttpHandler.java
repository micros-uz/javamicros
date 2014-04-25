package uz.micros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpHandler {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public HttpHandler(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        init();

        String msg;
        List<String> packet = new ArrayList<String>();

        try {
            msg = in.readLine();
            packet.add(msg);
            while (msg != null && !msg.equals("")) {
                msg = in.readLine();
                packet.add(msg);
            }
            handleHttpPacket(packet);
            this.socket.close();
        } catch (IOException e) {
            System.out.println("ERR");
            System.out.println(e);
        }
    }

    private void init() {
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleHttpPacket(List<String> packet) {
        for (String s : packet)
            System.out.println(s);

        /*

        HTTP/1.1 200 Ok
        Server: nginx
        Date: Fri, 25 Apr 2014 10:58:28 GMT
        Content-Type: text/html; charset=UTF-8

IIS localhost

HTTP/1.1 200 OK
Content-Type: text/html
Last-Modified: Tue, 25 Mar 2014 05:06:18 GMT
Accept-Ranges: bytes
ETag: "9dcdc6f4e747cf1:0"
Server: Microsoft-IIS/8.5
X-Powered-By: ASP.NET
Date: Fri, 25 Apr 2014 11:03:38 GMT
Content-Length: 694

         */

        out.println("HTTP/1.1 200 Ok");
        out.println("Server: nginx");
        out.println("Date: Fri, 25 Apr 2014 10:58:28 GMT");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("Accept-Ranges: bytes");
        out.println("Content-Length: 17");
        out.println();
        out.println("<h2>Hello!</h2>");
    }

}
