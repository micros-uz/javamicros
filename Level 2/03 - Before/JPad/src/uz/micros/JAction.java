package uz.micros;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JAction extends AbstractAction {

    private final CommandHandler cmdHandler;

    public JAction(Commands cmd, CommandHandler handler) {
        super(cmd.toString());
        putValue("cmd", cmd);
        cmdHandler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        cmdHandler.handle((Commands)getValue("cmd"));
    }
}
