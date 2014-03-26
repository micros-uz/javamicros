package uz.micros.gui;

import com.sun.javafx.image.BytePixelSetter;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
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

        messageTextPane.requestFocus();

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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                    sendText();
                    e.consume();
                }
            }
        });

        contactsList.setCellRenderer(new ContactsListCellRenderer());
    }

    private void sendText() {
        String text = messageTextPane.getText();
        messageTextPane.setText("");

        JTextPane textPane = getActiveTextTab();

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();

        StyleConstants.setForeground(attr, Color.BLUE);
        StyleConstants.setBold(attr, true);

        try {
            doc.insertString(doc.getLength(), "Main: \n", attr);
            doc.insertString(doc.getLength(), text + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private JTextPane getActiveTextTab() {
        int n = mainTabPane.getSelectedIndex();

        JScrollPane scrollPane = (JScrollPane) mainTabPane.getComponentAt(n);

        return (JTextPane) scrollPane.getViewport().getComponent(0);
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

        messageTextPane.requestFocus();
    }

    private void addTab(String contact) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        mainTabPane.addTab(contact, new JScrollPane(textPane));
        mainTabPane.setSelectedIndex(mainTabPane.getTabCount() - 1);
    }
}
