package uz.micros.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame{
    private JPanel rootPanel;
    private JTextPane messageTextPane;
    private JTabbedPane mainTabPane;
    private JList contactsList;
    private JTextPane mainTabTextPane;

    public MainWindow() {
        setContentPane(rootPanel);
        setSize(650, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        contactsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2)
                    selectTab();
            }
        });
        mainTabPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2)
                    closeTab();
            }
        });
        messageTextPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendText();
                }
            }
        });
    }

    private void sendText() {
        String text = messageTextPane.getText();
        messageTextPane.setText("");
        int n = mainTabPane.getSelectedIndex();
        JPanel panel = (JPanel)mainTabPane.getComponentAt(n);
        JScrollPane scrollPane = (JScrollPane)panel.getComponent(0);
        JTextPane textPane = (JTextPane)scrollPane.getViewport().getComponent(0);

        textPane.setText(text);
    }

    private void closeTab() {
        int n = mainTabPane.getSelectedIndex();

        if (n > 0)
            mainTabPane.remove(n);
    }

    private void selectTab() {
        String title = contactsList.getSelectedValue().toString();

        int n = mainTabPane.indexOfTab(title);

        if (n == -1)
            addTab(title);
        else
            mainTabPane.setSelectedIndex(n);
    }

    private void addTab(String contact){
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        mainTabPane.addTab(contact, new JScrollPane(textPane));
        mainTabPane.setSelectedIndex(mainTabPane.getTabCount() - 1);
    }
}
