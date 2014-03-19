package uz.micros;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.util.List;

public class FileSystemModel {
    private FileSystemView fileSystemView;

    public TreeModel getData() {

        fileSystemView = FileSystemView.getFileSystemView();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        File[] roots = fileSystemView.getRoots();
        for (File rt : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(rt);

            root.add(node);

            File[] files = fileSystemView.getFiles(rt, true);
            for (File f : files)
                node.add(new DefaultMutableTreeNode(f));
        }

        return new DefaultTreeModel(root);
    }

    public TreeSelectionListener getListener() {
        return new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object o = e.getPath().getLastPathComponent();

                showChildren(o);
            }
        };
    }

    private void showChildren(Object o) {
        final DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;

        //tree.setEnabled(false);
        SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
            @Override
            protected Void doInBackground() throws Exception {
                File f = (File) node.getUserObject();

                if (f.isDirectory()) {
                    File[] files = fileSystemView.getFiles(f, true);

                    if (node.isLeaf()) {
                        for (File child : files) {
                            publish(child);
                        }
                    }
                }

                return null;
            }

            @Override
            protected void process(List<File> chunks) {
                for (File child : chunks) {
                    node.add(new DefaultMutableTreeNode(child));
                }
            }

            @Override
            protected void done() {
                //tree.setEnabled(true);
            }
        };

        worker.execute();
    }
}
