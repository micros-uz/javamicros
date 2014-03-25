package uz.micros.core;

import uz.micros.gui.MainWindow;
import uz.micros.network.*;

import java.io.IOException;
import java.net.*;

public class ChatManager implements ServerSink, ClientSink {
    public void start() {
        new MainWindow();

        UdpServer udpServer = new UdpServer();
        udpServer.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyNewUser();

        new Server(7777, this).start();
    }

    private void notifyNewUser() {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            sendData = "new chat".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 65530);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connected(Socket s) {
        Thread t = new Thread(new Client(s, this));
        t.start();
    }

    @Override
    public void event(String addr, String event) {

    }
}
