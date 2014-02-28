package uz.micros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class JMain extends JFrame implements CommandHandler {

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createControls();
    }

    private void createControls() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        mnFile.setMnemonic('f');

        // old code - такого кода будет много, давайте переделаем
        Action aNew = new AbstractAction(Commands.New.toString()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmdHandler(e.getActionCommand());
            }
        };

        // new code
        aNew = new JAction(Commands.New, this);

        JMenuItem item = new JMenuItem(aNew);
        item.setMnemonic('n');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        mnFile.add(item);
        menuBar.add(mnFile);

        // Create toolbar
        JToolBar toolBar = new JToolBar();
        JButton bNew = new JButton(aNew);
        toolBar.add(bNew);
        toolBar.add(new JButton(aNew));
        toolBar.add(new JButton(aNew));
        toolBar.add(new JButton(aNew));
        getContentPane().add(toolBar, BorderLayout.NORTH);
    }

    private void cmdHandler(String actionCommand) {
        if (actionCommand == Commands.New.toString()){
            dispose();
        }
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void handle(Commands cmd) {
        switch(cmd){
            case New:
                dispose();
                break;
        }
    }
}
