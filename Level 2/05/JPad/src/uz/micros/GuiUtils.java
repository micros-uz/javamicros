package uz.micros;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.net.URL;

public class GuiUtils {

    public static JButton createButton(Action a, String name){
        JButton res = new JButton(a);

        URL url = JMain.class.getResource("images/" + name);
        Icon icon = new ImageIcon(url);

        res.setIcon(icon);
        res.setText("");
        res.setFocusPainted(false);

        return res;
    }

    public static JMenuItem createMenuItem(Action a, char m, int key, boolean shift) {
        JMenuItem res = new JMenuItem(a);
        res.setMnemonic(m);

        int mask = InputEvent.CTRL_MASK;

        if (shift) mask |= InputEvent.SHIFT_MASK;

        if (key != 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key, mask));

        return res;
    }

    public static JCheckBoxMenuItem createMenuItem2(Action a, char m, int key, boolean shift) {
        JCheckBoxMenuItem res = new JCheckBoxMenuItem(a);
        res.setMnemonic(m);

        int mask = InputEvent.CTRL_MASK;

        if (shift) mask |= InputEvent.SHIFT_MASK;

        if (key != 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key, mask));

        return res;
    }
}
