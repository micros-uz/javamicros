package uz.micros;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class JMain extends JFrame implements CommandPerformer {

    private Action aNew, aOpen, aSave, aSaveAs, aExit;
    private Action aCut, aCopy, aPaste;
    private JTextArea textArea;

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createActions();
        createMenu();
        createToolBar();
        createTextArea();
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setMargin(new Insets(4, 4, 4, 4));

        JScrollPane scroll = new JScrollPane(textArea);

        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void createActions() {
        aNew = new JAction(JCommands.New, this);
        aOpen = new JAction(JCommands.Open, this);
        aExit = new JAction(JCommands.Exit, this);

        aCut = new JAction(JCommands.Cut, this);
        aCopy = new JAction(JCommands.Copy, this);
        aPaste = new JAction(JCommands.Paste, this);
    }

    private JButton createButton(Action a, String name){
        JButton res = new JButton(a);

        URL url = JMain.class.getResource("images/" + name);
        Icon icon = new ImageIcon(url);

        res.setIcon(icon);
        res.setText("");
        res.setFocusPainted(false);

        return res;
    }

    private void createToolBar() {
        JToolBar tb = new JToolBar();
        tb.add(createButton(aNew, "new.png"));
        tb.add(createButton(aOpen, "open.png"));
        tb.addSeparator();
        tb.add(createButton(aOpen, "cut.png"));
        tb.add(createButton(aOpen, "copy.png"));
        tb.add(createButton(aOpen, "paste.png"));

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
        JMenuItem miExit = createMenuItem(aExit, 'x', 0);

        mnFile.add(miNew);
        mnFile.add(miOpen);
        mnFile.addSeparator();
        mnFile.add(miExit);
    }

    private JMenuItem createMenuItem(Action a, char m, int key) {
        JMenuItem res = new JMenuItem(a);
        res.setMnemonic(m);

        if (key != 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key,
                    InputEvent.CTRL_MASK));

        return res;
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void handle(JCommands cmd) {
        switch(cmd){
            case New:
                break;
            case Open:
                handleOpen();
                break;
            case Exit:
                dispose();
                break;
        }
    }

    private void handleOpen() {
        JFileChooser dlg = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "log", "java");
        dlg.setFileFilter(filter);

        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File file = dlg.getSelectedFile();

            try {
                FileReader reader = new FileReader(file);
                textArea.read(reader, null);
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
