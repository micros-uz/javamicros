package uz.micros;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;

public class JMain extends JFrame implements CommandPerformer {

    private Action aNew, aOpen, aSave, aExit;
    private Action aCut, aCopy, aPaste;

    private JTextArea textArea;
    private File curFile;
    private boolean onTop;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private FileSystemView fileSystemView;

    public JMain() {
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createActions();
        createMenu();
        createToolBar();
        createTextArea();
        createTree();
        createSplitter();
    }

    private void createSplitter() {
        JScrollPane treeScroll = new JScrollPane(tree);
        JScrollPane textScroll = new JScrollPane(textArea);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                treeScroll, textScroll);

        getContentPane().add(split, BorderLayout.CENTER);
    }

    private void createTree() {
        fileSystemView = FileSystemView.getFileSystemView();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);

        TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent tse){
                DefaultMutableTreeNode node =
                        (DefaultMutableTreeNode)tse.getPath().getLastPathComponent();
                showChildren(node);
            }
        };

        // show the file system roots.
        File[] roots = fileSystemView.getRoots();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add( node );

            File[] files = fileSystemView.getFiles(fileSystemRoot, true);
            for (File file : files) {
                    node.add(new DefaultMutableTreeNode(file));
            }
        }

        tree = new JTree(treeModel);
        tree.setRootVisible(false);
        tree.addTreeSelectionListener(treeSelectionListener);
        tree.setCellRenderer(new FileTreeCellRenderer());
        tree.expandRow(0);

    }

    /** Add the files that are contained within the directory of this node.
     Thanks to Hovercraft Full Of Eels. */
    private void showChildren(final DefaultMutableTreeNode node) {
        tree.setEnabled(false);
        //progressBar.setVisible(true);
        //progressBar.setIndeterminate(true);

        SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
            @Override
            public Void doInBackground() {
                File file = (File) node.getUserObject();
                if (file.isDirectory()) {
                    File[] files = fileSystemView.getFiles(file, true); //!!
                    if (node.isLeaf()) {
                        for (File child : files) {
                            if (child.isDirectory()) {
                                publish(child);
                            }
                        }
                    }
                    //setTableData(files);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<File> chunks) {
                for (File child : chunks) {
                    node.add(new DefaultMutableTreeNode(child));
                }
            }

            @Override
            protected void done() {
                //progressBar.setIndeterminate(false);
               // progressBar.setVisible(false);
                tree.setEnabled(true);
            }
        };
        worker.execute();
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setMargin(new Insets(4, 4, 4, 4));
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

        JCheckBoxMenuItem miOnTop = createMenuItem2(new AbstractAction("Always On Top") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem c = (JCheckBoxMenuItem)e.getSource();
                onTop = !onTop;
                setAlwaysOnTop(onTop);
                c.setSelected(onTop);
            }
        }, 't', 0);

        JCheckBoxMenuItem miTree = createMenuItem2(new AbstractAction("Show Tree") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //split.setOneTouchExpandable(true);
               // split.setDividerLocation(0.0);
                ProcessBuilder pb = new ProcessBuilder("cmd.exe");
                Runtime rt = Runtime.getRuntime();

                try {
                    rt.exec(new String[]{"cmd.exe", "/c", "start"});
                    //pb.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }, 'r', 0);

        // http://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html#ToolBarDemo

        // http://docs.oracle.com/javase/tutorialJWS/samples/uiswing/DynamicTreeDemoProject/DynamicTreeDemo.jnlp

        mnView.add(miOnTop);
        mnView.add(miTree);
    }

    private JCheckBoxMenuItem createMenuItem2(Action a, char m, int key) {
        JCheckBoxMenuItem res = new JCheckBoxMenuItem(a);
        res.setMnemonic(m);

        if (key > 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key, InputEvent.CTRL_MASK));

        return res;
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

    private boolean d;
    private void handleNew(){
        if (JOptionPane.showConfirmDialog(this, "Sure?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION)
            setAlwaysOnTop(d = !d);
        //JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
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
