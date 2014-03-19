package uz.micros;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JAction extends AbstractAction {
    private final CommandPerformer cmdHandler;
    private final JCommands cmd;

    public JAction(JCommands cmd, CommandPerformer handler) {
        super(cmd.toString());
        this.cmd = cmd;
        cmdHandler = handler;
    }

    public void actionPerformed(ActionEvent e) {
        cmdHandler.handle(cmd);
    }
}
