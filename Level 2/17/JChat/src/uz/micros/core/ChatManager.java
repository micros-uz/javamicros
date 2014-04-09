package uz.micros.core;

import uz.micros.gui.GuiEventSink;
import uz.micros.gui.MainWindow;
import uz.micros.network.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChatManager
        implements ServerSink, ClientSink, GuiEventSink, ConnectorSink {
    private MainWindow mainWindow;
    private String hostUserName = "Ustoz";//"先生";
    private List<Client> clients = new ArrayList<Client>();
    private int serverPort = 65530;

    public void start() {
        mainWindow = new MainWindow(hostUserName, this);

        startConnectors();
        new Server(serverPort, this).start();
    }

    private void startConnectors() {
        try {
            byte[] local = InetAddress.getLocalHost().getAddress();

            for (int k = local[3] + 257; k <= 150; k++){
                InetAddress ia = getAddr(local, k);
                Connector conn = new Connector(ia, serverPort, this);
                System.out.println(k);
                new Thread(conn).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private InetAddress getAddr(byte[] local, int b) {
        byte[] addr = Arrays.copyOf(local, 4);
        addr[3] = (byte)b;

        InetAddress res = null;
        try {
            res = InetAddress.getByAddress(addr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public void newConnection(Socket sock) {
        Client client = new Client(sock, this);

        Thread thread = new Thread(client);

        thread.start();
    }

    @Override
    public void notifyEvent(ClientEvent clientEvent) {
        switch (clientEvent.getType()) {
            case Connect:
                //mainWindow.newConnection(data)
                break;
            case Login: {
                boolean exists = false;
                String name = clientEvent.getClient().getName();

                exists = clients.stream().anyMatch(c -> c.getName().equals(name));

                if (!exists) {
                    clients.add(clientEvent.getClient());
                    mainWindow.newClient(clientEvent.getData());
                } else {
                    clientEvent.getClient().send("You are a fake!");
                    clientEvent.getClient().destroy();
                }
            }
            break;
            case Message:
                mainWindow.newMessage(clientEvent.getData(), clientEvent.getClient().getName());
                break;
            case Disconnect:
                String name2 = clientEvent.getClient().getName();
                Client clToRemove = null;

                for (Client c : clients) {
                    if (c.getName().equals(name2)) {
                        clToRemove = c;
                        break;
                    }
                }

                if (clToRemove != null)
                    clients.remove(clToRemove);

                mainWindow.destroyClient(name2);
                break;
        }
    }

    @Override
    public void guiEvent(String msg, String userName) {
        if (userName == null) {
            clients.stream().forEach(x -> x.send(msg));
        } else {
            clients.stream().filter(x -> x.getName().
                    equals(userName)).findFirst().get().send(msg);
        }

    }

    @Override
    public String getHostName() {
        return hostUserName;
    }

    @Override
    public void connected(Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(hostUserName);
            Client client = new Client(socket, this);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
