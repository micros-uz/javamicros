package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyL implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
    }
}
