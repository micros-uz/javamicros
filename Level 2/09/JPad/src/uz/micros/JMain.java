package uz.micros;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class JMain extends JFrame implements CommandPerformer {

    private final String APP_NAME = "JPad";
    private final Editor editor;
    private final Jvm jvm;

    private Action aNew, aOpen, aSave, aSaveAs, aExit;
    private Action aCut, aCopy, aPaste;
    private JAction aCompile, aRun;

    private JTextArea textArea;
    private JTree tree;
    private FileSystemView fileSystemView;
    private DefaultTreeModel treeModel;
    private FileSystemModel fileSystemModel;
    private JTextArea outPane;

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createActions();
        createMenu();
        createToolBar();
        createTextArea();
        createTree();
        createOutPane();
        createSplit();

        editor = new Editor(textArea, this);
        editor.addFileChangedListener(new FileChangedListener() {
            @Override
            public void fileChanged(String path) {
                setTitle(APP_NAME + " - " + path);
            }
        });

        jvm = new Jvm();
        jvm.addListener(new JvmListener() {
            @Override
            public void send(JvmEvent event, String data) {
                switch (event){
                    case Start:
                        outPane.setText("");
                        addText("Compilation started at " + data);
                        break;
                    case Error:
                        addText(data);
                        break;
                    case End:
                        addText("Compilation ended at " + data);
                        break;
                }
            }

            private void addText(String data) {
                String text = outPane.getText();
                outPane.setText(text + "\r\n" + data);
            }
        });

        setTitle(APP_NAME);
    }

    private void createOutPane() {
        outPane = new JTextArea();
        outPane.setEditable(false);
        outPane.setBackground(new Color(218, 214, 211));
    }

    private void createSplit() {

        JScrollPane scrollTree = new JScrollPane(tree);
        JScrollPane scrollText = new JScrollPane(textArea);
        JScrollPane scrollOut = new JScrollPane(outPane);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollTree, scrollText);

        split.setDividerLocation(150);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                split, scrollOut);

        mainSplit.setDividerLocation(300);

        getContentPane().add(mainSplit, BorderLayout.CENTER);
    }

    private void createTree() {
        fileSystemModel = new FileSystemModel();

        tree = new JTree(fileSystemModel.getData());
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(fileSystemModel.getListener());
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if(selRow != -1 && e.getClickCount() == 2) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)selPath.getLastPathComponent();

                    editor.open(node.getUserObject().toString());
                }
            }
        });
        tree.setCellRenderer(new FileTreeRenderer());
        tree.expandRow(0);
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setMargin(new Insets(4, 4, 4, 4));

        textArea.setFont(new Font("Lucida Console", Font.PLAIN, 18));
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

        aCompile = new JAction(JCommands.Compile, this);
        aRun = new JAction(JCommands.Run, this);
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
        JMenu mnBuild = new JMenu("Buld");
        mnBuild.setMnemonic('b');
        JMenu mnTools = new JMenu("Tools");
        mnTools.setMnemonic('t');
        JMenu mnHelp = new JMenu("Help");
        mnHelp.setMnemonic('h');

        menuBar.add(mnFile);
        menuBar.add(mnEdit);
        menuBar.add(mnView);
        menuBar.add(mnBuild);
        menuBar.add(mnTools);
        menuBar.add(mnHelp);

        JMenuItem miNew = GuiUtils.createMenuItem(aNew, 'n', KeyEvent.VK_N, HotKey.Ctrl);
        JMenuItem miOpen = GuiUtils.createMenuItem(aOpen, 'o', KeyEvent.VK_O, HotKey.Ctrl);
        JMenuItem miSave = GuiUtils.createMenuItem(aSave, 's', KeyEvent.VK_S, HotKey.Ctrl);
        JMenuItem miSaveAs = GuiUtils.createMenuItem(aSaveAs, 's', KeyEvent.VK_S, HotKey.CtrlShift);
        JMenuItem miExit = GuiUtils.createMenuItem(aExit, 'x', 0, HotKey.Ctrl);

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
        }, 't', 0, HotKey.Ctrl);

        mnView.add(miOnTop);

        JMenuItem miCompile = GuiUtils.createMenuItem(aCompile, 'c', KeyEvent.VK_F9, HotKey.Ctrl);
        JMenuItem miRun = GuiUtils.createMenuItem(aRun, 'c', KeyEvent.VK_F10, HotKey.Shift);

        mnBuild.add(miCompile);
        mnBuild.add(miRun);

        JMenuItem miConsole = GuiUtils.createMenuItem(new AbstractAction("Command Prompt...") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime rt = Runtime.getRuntime();

                try {
                    rt.exec(new String[]{"cmd.exe", "/c", "start"});
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }, 'c', 0, HotKey.Ctrl);

        mnTools.add(miConsole);
    }

    public void display() {
        setVisible(true);
    }

    @Override
    public void handle(JCommands cmd) {
        switch (cmd) {
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
            case Compile:
                if (editor.hasJavaFile())
                    jvm.compile(editor.getFile());
                else
                    JOptionPane.showMessageDialog(this, "This is not a valid Java file", APP_NAME, JOptionPane.ERROR_MESSAGE);
                break;
            case Run:
                jvm.run();
                break;
        }
    }
}
