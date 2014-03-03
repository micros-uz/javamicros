package uz.micros;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JAction extends AbstractAction {
    private final CommandPerformer cmdHandler;
    private final JCommands command;

    public JAction(JCommands cmd, Icon icon, CommandPerformer handler) {
        super(cmd.toString(), icon);

        command = cmd;
        cmdHandler = handler;
    }

    public void actionPerformed(ActionEvent e) {
        cmdHandler.handle(command);
    }
}
