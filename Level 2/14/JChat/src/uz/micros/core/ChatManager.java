package uz.micros.core;

import uz.micros.gui.MainWindow;
import uz.micros.network.*;

import java.net.Socket;

public class ChatManager implements ServerSink, ClientSink {
    private MainWindow mainWindow;

    public void start() {
        mainWindow = new MainWindow();

        new Server(65530, this).start();
    }

    @Override
    public void newClient(Socket sock) {
        Thread thread = new Thread(new Client(sock, this));

        thread.start();
    }

    @Override
    public void notifyEvent(ClientEvent clientEvent) {
        switch (clientEvent.getType()){
            case Connect:
                //mainWindow.newConnection(data)
                break;
            case Login:
                mainWindow.newClient(clientEvent.getData());
                break;
            case Message:
                mainWindow.newMessage(clientEvent.getData());
                break;
            case Disconnect:
                break;
        }
    }
}
