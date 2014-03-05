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

    private final Editor editor;
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

        editor = new Editor(textArea, this);
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
        aSave = new JAction(JCommands.Save, this);
        aSaveAs = new JAction(JCommands.SaveAs, this);
        aExit = new JAction(JCommands.Exit, this);

        aCut = new JAction(JCommands.Cut, this);
        aCopy = new JAction(JCommands.Copy, this);
        aPaste = new JAction(JCommands.Paste, this);
    }

    private void createToolBar() {
        JToolBar tb = new JToolBar();

        tb.add(GuiUtils.createButton(aNew, "new.png"));
        tb.add(GuiUtils.createButton(aOpen, "open.png"));
        tb.add(GuiUtils.createButton(aSave, "open.png"));
        tb.addSeparator();
        tb.add(GuiUtils.createButton(aCut, "cut.png"));
        tb.add(GuiUtils.createButton(aCopy, "copy.png"));
        tb.add(GuiUtils.createButton(aPaste, "paste.png"));

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

        JMenuItem miNew = GuiUtils.createMenuItem(aNew, 'n', KeyEvent.VK_N, false);
        JMenuItem miOpen = GuiUtils.createMenuItem(aOpen, 'o', KeyEvent.VK_O, false);
        JMenuItem miSave = GuiUtils.createMenuItem(aSave, 's', KeyEvent.VK_S, false);
        JMenuItem miSaveAs = GuiUtils.createMenuItem(aSaveAs, 's', KeyEvent.VK_S, true);
        JMenuItem miExit = GuiUtils.createMenuItem(aExit, 'x', 0, false);

        mnFile.add(miNew);
        mnFile.add(miOpen);
        mnFile.add(miSave);
        mnFile.add(miSaveAs);
        mnFile.addSeparator();
        mnFile.add(miExit);

        JCheckBoxMenuItem miOnTop = GuiUtils.createMenuItem2(new AbstractAction("Always On Top") {
            boolean onTop;
            @Override
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(onTop = !onTop);
            }
        }, 't', 0, false);

        mnView.add(miOnTop);
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void handle(JCommands cmd) {
        switch(cmd){
            case New:
                editor.create();
                break;
            case Open:
                editor.open();
                break;
            case Save:
                editor.save();
                break;
            case SaveAs:
                editor.saveAs();
                break;
            case Exit:
                dispose();
                break;
        }
    }


}
