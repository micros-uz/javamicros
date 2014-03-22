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

    public static JMenuItem createMenuItem(Action a, char m, int key, HotKey hotKey) {
        JMenuItem res = new JMenuItem(a);
        res.setMnemonic(m);

        int mask = getMask(hotKey);

        if (key != 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key, mask));

        return res;
    }

    private static int getMask(HotKey hotKey) {
        int mask = 0;
        switch (hotKey){
            case Ctrl:
                mask = InputEvent.CTRL_MASK;
                break;
            case Shift:
                mask = InputEvent.SHIFT_MASK;
                break;
            case CtrlShift:
                mask = InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK;
                break;
        }
        return mask;
    }

    public static JCheckBoxMenuItem createMenuItem2(Action a, char m, int key, HotKey hotKey) {
        JCheckBoxMenuItem res = new JCheckBoxMenuItem(a);
        res.setMnemonic(m);

        int mask = getMask(hotKey);

        if (key != 0)
            res.setAccelerator(KeyStroke.getKeyStroke(key, mask));

        return res;
    }
}
