package uz.micros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

public class JMain extends JFrame implements CommandPerformer {

    private Action aNew;
    private Action aOpen;

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createActions();
        createMenu();
        createToolBar();
    }

    private void createActions() {
        aNew = new JAction("New", this);
        aOpen = new JAction("Open", this);
    }

    private void createToolBar() {
        JToolBar tb = new JToolBar();
        JButton bNew = new JButton(aNew);
        tb.add(bNew);

        getContentPane().add(tb, BorderLayout.NORTH);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        mnFile.setMnemonic('f');
        JMenu mnEdit = new JMenu("Edit");
        mnEdit.setMnemonic('e');
        JMenu mnView = new JMenu("View");
        mnView.setMnemonic('v');
        JMenu mnTools = new JMenu("Tools");
        mnTools.setMnemonic('t');
        JMenu mnHelp = new JMenu("Help");
        mnHelp.setMnemonic('h');

        menuBar.add(mnFile);
        menuBar.add(mnEdit);
        menuBar.add(mnView);
        menuBar.add(mnTools);
        menuBar.add(mnHelp);

        JMenuItem miNew = createMenuItem(aNew, 'n', KeyEvent.VK_N);
        JMenuItem miOpen = createMenuItem(aOpen, 'o', KeyEvent.VK_O);

        mnFile.add(miNew);
        mnFile.add(miOpen);
    }

    private JMenuItem createMenuItem(Action a, char m, int key) {
        JMenuItem res = new JMenuItem(a);
        res.setMnemonic(m);
        res.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_MASK));

        return res;
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void handle(String actionCommand) {
        setTitle(actionCommand);
    }
}
