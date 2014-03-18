package uz.micros;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class JMain extends JFrame implements CommandPerformer {

    private static final String APP_NAME = "JPad";
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
    private JTextArea outputPane;
    private JStatusBar statusBar;

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createActions();
        createMenu();
        createToolBar();
        createTextArea();
        createTree();
        createOutputPane();
        createSplit();
        createStatus();

        setTitle(APP_NAME);

        editor = new Editor(textArea, this);
        editor.addFileChangedListener(new FileChangedListener() {
            @Override
            public void fileChanged(String path, long size) {
                setTitle(APP_NAME + " - " + path);
                //setLeftLabel(size + " bytes");
            }
        });

        jvm = new Jvm();
        jvm.addListener(new JvmListener() {
            @Override
            public void sendEvent(JvmEvent type, String data) {
                switch(type){
                    case Ok:
                        addText("Done");
                        break;
                    case Error:
                        addText(data);
                        break;
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               // dispose();
            }
        });
    }

    private void addText(String st) {
        outputPane.append(st + "\n\r");
    }

    private void setLeftLabel(String s) {
        statusBar.setLabelText(s);
    }

    private void createStatus() {
        //statusBar = new JPanel();
        //statusBar.setLabelText("Started");

        getContentPane().add(new JPanel(), BorderLayout.SOUTH);
    }

    private void createOutputPane() {
        outputPane = new JTextArea();
        outputPane.setEditable(false);
    }

    private void createSplit() {
        JScrollPane scrollTree = new JScrollPane(tree);
        JScrollPane scrollText = new JScrollPane(textArea);
        JScrollPane scrollOutput = new JScrollPane(outputPane);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollTree, scrollText);

        split.setDividerLocation(150);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                split, scrollOutput);
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

                    DefaultMutableTreeNode nd = (DefaultMutableTreeNode)selPath.getLastPathComponent();

                    editor.open(nd.getUserObject().toString());
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
                    JOptionPane.showMessageDialog(this, "This is not a valid java file", "JPad", JOptionPane.ERROR_MESSAGE);
                break;
            case Run:
                jvm.run();
                break;
        }
    }
}
