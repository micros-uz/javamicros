package uz.micros.network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Connector implements Runnable {

    private final int port;
    private final InetAddress addr;
    private final ConnectorSink sink;

    public Connector(InetAddress addr, int port, ConnectorSink sink) {
        this.addr = addr;
        this.port = port;
        this.sink = sink;
    }

    private void connect() {
        try {
            Socket sock = new Socket(addr, port);

            sink.connected(sock);
        } catch(ConnectException e){

        } catch (IOException e) {

        } catch (SecurityException e) {

        } catch (IllegalArgumentException e) {

        } catch (NullPointerException e) {

        }

        System.out.println("Connect to " + addr.getHostAddress() + " failed");
    }

    @Override
    public void run() {
        connect();
    }
}
