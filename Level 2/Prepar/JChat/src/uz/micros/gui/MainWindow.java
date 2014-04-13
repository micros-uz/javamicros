package uz.micros.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private final GuiEventSink sink;
    private JPanel rootPanel;
    private JTextPane messageTextPane;
    private JTabbedPane mainTabPane;
    private JList contactsList;
    private JTextPane mainTabTextPane;
    private DefaultListModel contacts;
    private final String hostUserName;
    private final JTabBlinker tabBlinker;

    public MainWindow(String userName, GuiEventSink sink) {
        hostUserName = userName;
        this.sink = sink;
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
                if (e.getClickCount() == 2) {
                    closeTab();
                    new JTabBlinker(mainTabPane).blink(0);
                }
            }
        });

        mainTabPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tabBlinker.stop(getSelTabIndex());
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

        contacts = new DefaultListModel<String>();

        contactsList.setModel(contacts);
        tabBlinker = new JTabBlinker(mainTabPane);
    }

    private int getSelTabIndex() {
        return mainTabPane.getSelectedIndex();
    }

    private void sendText() {
        String text = messageTextPane.getText();

        if (text.length() > 0) {
            messageTextPane.setText("");

            JTextPane textPane = getActiveTextTab();

            setTabText(text, textPane, hostUserName);

            int n = getSelTabIndex();
            String title = null;
            if (n > 0) title = mainTabPane.getTitleAt(n);

            sink.guiEvent(text, title);
        }
    }

    private void setTabText(String text, JTextPane textPane, String userName) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();

        StyleConstants.setForeground(attr, Color.BLUE);
        StyleConstants.setBold(attr, true);

        try {
            doc.insertString(doc.getLength(), userName + ": \n", attr);
            doc.insertString(doc.getLength(), text + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private JTextPane getActiveTextTab() {
        return getTextPane(getSelTabIndex());
    }

    private JTextPane getTextPane(int n) {
        JScrollPane scrollPane = (JScrollPane) mainTabPane.getComponentAt(n);

        return (JTextPane) scrollPane.getViewport().getComponent(0);
    }

    private void closeTab() {
        removeTab(getSelTabIndex());
    }

    private void removeTab(int n) {
        if (n > 0)
            mainTabPane.remove(n);
    }

    private void selectTab() {
        String title = contactsList.getSelectedValue().toString();

        int n = findTab(title);

        if (n == -1)
            addTab(title);
        else
            mainTabPane.setSelectedIndex(n);

        messageTextPane.requestFocus();
    }

    private int findTab(String title) {
        return mainTabPane.indexOfTab(title);
    }

    private void addTab(String contact) {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        mainTabPane.addTab(contact, new JScrollPane(textPane));
        mainTabPane.setSelectedIndex(mainTabPane.getTabCount() - 1);
    }

    public void newClient(String userName) {
        addTab(userName);
        contacts.addElement(userName);
    }

    public void newMessage(String msg, String userName) {
        int n = findTab(userName);

        if (n > -1) {
            JTextPane textPane = getTextPane(n);

            setTabText(msg, textPane, userName);

            if (n != getSelTabIndex())
                tabBlinker.blink(n);
        }
    }

    public void destroyClient(String userName) {
        contacts.removeElement(userName);

        int n = findTab(userName);

        removeTab(n);
    }
}
