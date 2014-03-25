package uz.micros.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ContactsListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel res = new JLabel();

        res.setText(value.toString());
        res.setOpaque(true);
        URL url = MainWindow.class.getResource("images/user.png");
        Icon icon = new ImageIcon(url);
        res.setIcon(icon);

        if (isSelected) {
            res.setBackground(list.getSelectionBackground());
            res.setForeground(list.getSelectionForeground());
        } else {
            res.setBackground(list.getBackground());
            res.setForeground(list.getForeground());
        }

        return res;
    }
}
