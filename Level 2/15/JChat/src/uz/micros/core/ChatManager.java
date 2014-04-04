package uz.micros.core;

import uz.micros.gui.GuiEventSink;
import uz.micros.gui.MainWindow;
import uz.micros.network.*;

import java.net.Socket;
import java.util.List;


public class ChatManager
        implements ServerSink, ClientSink, GuiEventSink {
    private MainWindow mainWindow;
    private String hostUserName = "Ustoz";//"先生";
    private List<Client> clients;

    public void start() {
        mainWindow = new MainWindow(hostUserName, this);

        new Server(65530, this).start();
    }

    @Override
    public void newConnection(Socket sock) {
        Client client = new Client(sock, this);


        Thread thread = new Thread(client);

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
                mainWindow.newMessage(clientEvent.getData(), clientEvent.getName());
                break;
            case Disconnect:
                mainWindow.destroyClient(clientEvent.getName());
                break;
        }
    }

    @Override
    public void guiEvent(String msg, String userName) {

    }
}
