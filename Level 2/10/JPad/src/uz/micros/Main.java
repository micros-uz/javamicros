package uz.micros;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();

        for(UIManager.LookAndFeelInfo lf : looks ){

            if ("Metal".equals(lf.getName()))
                try {
                    UIManager.setLookAndFeel(lf.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
        }


        JMain mainWindow = new JMain();
        mainWindow.display();
    }
}
