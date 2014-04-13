package uz.micros.gui;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JTabBlinker  {
    private Color defaultBackColor;
    private final JTabbedPane pane;
    private int tabIndex;
    private List<Pair<Integer, Timer>> timers = new ArrayList<>();

    public JTabBlinker(JTabbedPane pane) {
        this.pane = pane;
    }

    public void stop(int tab){
        Optional<Pair<Integer, Timer>> pair = timers.stream()
                .filter(x -> x.getKey() == tab).findAny();

        if (pair.isPresent()) {
            pair.get().getValue().stop();
            timers.remove(pair.get());
            pane.setBackgroundAt(tab, defaultBackColor);
        }
    }

    public void blink(int tab) {
        tabIndex = tab;
        defaultBackColor = pane.getBackgroundAt(tab); // default background color of tab

        Timer timer = new Timer(500, new ActionListener() {
            boolean blinkFlag = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                blink(blinkFlag);
                blinkFlag = !blinkFlag;
            }
        });
        timer.start();

        timers.add(new Pair<>(tab, timer));
    }

    private void blink(boolean blinkFlag) {
        pane.setBackgroundAt(tabIndex, blinkFlag
                ? new Color(89, 120, 225)
                : defaultBackColor);

        pane.repaint();
    }
}
