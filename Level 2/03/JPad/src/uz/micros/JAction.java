package uz.micros;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JAction extends AbstractAction {
    private final CommandPerformer cmdHandler;

    public JAction(String name, CommandPerformer handler) {
        super(name);
        cmdHandler = handler;
    }

    public void actionPerformed(ActionEvent e) {
        cmdHandler.handle(e.getActionCommand());
    }
}
