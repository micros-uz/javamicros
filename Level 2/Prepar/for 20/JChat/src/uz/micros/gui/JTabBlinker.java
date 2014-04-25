package uz.micros.gui;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class JTabBlinker {
    private final JTabbedPane pane;
    private int tabIndex;
    private Color defaultBackColor;
    private List<Pair<Integer, Timer>> timers = new ArrayList<>();

    public JTabBlinker(JTabbedPane pane) {
        this.pane = pane;
    }

    public void blink(int tabIndex) {
        this.tabIndex = tabIndex;
        defaultBackColor = pane.getBackgroundAt(tabIndex);

        Timer timer = new Timer(500, new ActionListener() {
            boolean flag = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                blink(flag);
                flag = !flag;
            }
        });

        timer.start();

        timers.add(new Pair(tabIndex, timer));
    }

    private void blink(boolean flag) {
        pane.setBackgroundAt(tabIndex, flag
            ? new Color(89, 120, 225) : defaultBackColor);

        pane.repaint();
    }

    public void stop(int tabIndex) {
        Optional<Pair<Integer, Timer>> pair = timers.stream()
                .filter(x -> x.getKey() == tabIndex).findAny();

        if (pair.isPresent()){
            pair.get().getValue().stop();
            timers.remove(pair.get());
            pane.setBackgroundAt(tabIndex, defaultBackColor);
        }
    }
}
