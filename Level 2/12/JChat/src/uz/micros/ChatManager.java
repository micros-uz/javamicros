package uz.micros;

import uz.micros.gui.MainWindow;
import uz.micros.network.Server;

public class ChatManager {
    public void start() {
        new MainWindow();

        new Server(65530).start();
    }
}
