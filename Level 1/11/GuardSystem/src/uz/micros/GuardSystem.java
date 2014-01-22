package uz.micros;

import uz.micros.modules.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GuardSystem {
    private ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    public void run() {
        System.out.println("Guard System Micros MChJ 2014 " + '\u00A9' + " v1.0");

        System.out.println("Enter your code:");

        String s = new Scanner(System.in).nextLine();

        loadModules();

        boolean valid = checkCode(s);

        System.out.println("Your code is "
                + (valid ? "" : "in")
                + "valid");
    }

    private void loadModules() {
        plugins.add(new IIVModule());
        plugins.add(new MXXModule());
    }

    private boolean checkCode(String s) {
        boolean res = true;
        for(Plugin p:plugins){
            System.out.println("MS# Sent to " + p.getClass().getName() + " " + s);
            p.send(s);

            if (PluginEx.class.isAssignableFrom(p.getClass())){
                res = res && ((PluginEx)p).allow(s);
                System.out.println("MS# " + p.getClass().getName() + " says: " + res);
            }
        }

        return res && s.length() >= 8;
    }
}
