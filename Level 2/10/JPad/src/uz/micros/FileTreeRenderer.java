package uz.micros;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.io.File;

public class FileTreeRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        FileSystemView fsv = FileSystemView.getFileSystemView();

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;

        File file = (File)node.getUserObject();

        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setIcon(fsv.getSystemIcon(file));
        label.setText(fsv.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());

        if (selected){
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        }else{
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }

        return label;
    }
}
