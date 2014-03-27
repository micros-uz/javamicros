package uz.micros.core;

import uz.micros.gui.MainWindow;
import uz.micros.network.Client;
import uz.micros.network.ClientSink;
import uz.micros.network.Server;
import uz.micros.network.ServerSink;

import java.net.Socket;

public class ChatManager implements ServerSink, ClientSink {
    public void start() {
        new MainWindow();

        new Server(65530, this).start();
    }

    @Override
    public void newClient(Socket socket) {
        Thread t = new Thread(new Client(socket, this));
        t.start();
    }

    @Override
    public void event(Client client, String msg) {
        System.out.println(client.getAddr());
    }
}
