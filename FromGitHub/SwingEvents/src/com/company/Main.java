package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public Main()  {
        super("FirstEvents");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addKeyListener(new KeyL());

        setSize(200, 200);

        setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}
