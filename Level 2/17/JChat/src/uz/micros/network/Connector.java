package uz.micros.network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Connector implements Runnable {
    private final InetAddress addr;
    private final int port;
    private final ConnectorSink sink;

    public Connector(InetAddress ia,
                     int port, ConnectorSink sink){
        addr = ia;
        this.port = port;
        this.sink = sink;
    }

    @Override
    public void run()  {
        Socket socket = null;
        try {
            socket = new Socket(addr, port);

            sink.connected(socket);
        } catch (IOException e) {
        } catch (SecurityException e ){
        } catch (IllegalArgumentException e){
        } catch (NullPointerException e){
        }

        if (socket == null)
            System.out.println("Connect to " + addr.getHostAddress()
                + " failed");
    }
}
