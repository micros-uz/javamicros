package uz.micros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;

public class JMain extends JFrame implements CommandPerformer {

    private Action aNew, aOpen, aSave, aExit;
    private Action aCut, aCopy, aPaste;

    private JTextArea textArea;
    private File curFile;

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

    private Action createAction(JCommands cmd){
        return new JAction(cmd, null, this);
    }

    private void createActions() {
        aNew = createAction(JCommands.New);
        aOpen = createAction(JCommands.Open);
        aSave = createAction(JCommands.Save);
        aExit = createAction(JCommands.Exit);

        aCut = createAction(JCommands.Cut);
        aCopy = createAction(JCommands.Copy);
        aPaste = createAction(JCommands.Paste);
    }

    private JButton createButton(Action a, String path){
        URL imageURL = JMain.class.getResource("images/" + path);
        Icon icon = new ImageIcon(imageURL, "");

        JButton res = new JButton(a);
        res.setFocusPainted(false);
        res.setIcon(icon);
        res.setText("");

        return res;
    }

    private void createToolBar() {
        JToolBar tb = new JToolBar();
        tb.add(createButton(aNew, "new.png"));
        tb.add(createButton(aOpen, "open.png"));
        tb.addSeparator();
        tb.add(createButton(aCut, "cut.png"));
        tb.add(createButton(aCopy, "copy.png"));
        tb.add(createButton(aPaste, "paste.png"));

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
        JMenuItem miSave = createMenuItem(aSave, 's', KeyEvent.VK_S);
        JMenuItem miExit = createMenuItem(aExit, 'x', 0);

        mnFile.add(miNew);
        mnFile.add(miOpen);
        mnFile.add(miSave);
        mnFile.addSeparator();
        mnFile.add(miExit);
    }

    private JMenuItem createMenuItem(Action a, char m, int key) {
        JMenuItem res = new JMenuItem(a);
        res.setMnemonic(m);

        if (key > 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_MASK));

        return res;
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void handle(JCommands actionCommand) {
        switch(actionCommand){
            case New: handleNew(); break;
            case Open: handleOpen(); break;
            case Save: handleSave(); break;
            case Exit: dispose();
        }
    }

    private void handleNew(){
        if (JOptionPane.showConfirmDialog(null, "Sure?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION)
        JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
    }

    private void handleOpen(){
        JFileChooser dlg = new JFileChooser(new File("."));

        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File f = dlg.getSelectedFile();

            try {
                FileReader fr = new FileReader(f);
                textArea.read(fr, null);
                curFile = f;
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSave(){
        if (curFile != null){
            try {
                FileWriter fw = new FileWriter(curFile);
                textArea.write(fw);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
