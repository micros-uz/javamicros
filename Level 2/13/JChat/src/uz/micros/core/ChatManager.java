package uz.micros.core;

import uz.micros.gui.MainWindow;
import uz.micros.network.Client;
import uz.micros.network.Server;
import uz.micros.network.ServerSink;

import java.net.Socket;

public class ChatManager implements ServerSink {
    public void start() {
        new MainWindow();

        new Server(65530, this).start();
    }

    @Override
    public void newClient(Socket sock) {
        Thread thread = new Client(sock);

        thread.start();
    }
}
