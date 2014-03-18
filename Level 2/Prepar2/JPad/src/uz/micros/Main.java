package uz.micros;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            for (int k = 0; k < installedLookAndFeels.length; k++) {
                System.out.println(installedLookAndFeels[k]);
                if ("Windows Classic".equals(installedLookAndFeels[k].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[k].getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }

        JMain mainWindow = new JMain();
        mainWindow.display();
    }
}
