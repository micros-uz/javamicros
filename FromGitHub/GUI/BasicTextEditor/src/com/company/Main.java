package com.company;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/*******************************************************************************
 * * Simple Text Editor * ***********************
 *
 * @author SUNRAYS Developer
 * @URL : www.sunrays.co.in
 *
 * Copyright (c) sunRays Technologies. All rights reserved.
 */

public class Main extends JFrame {

    public static final String APP_NAME = "SUNRAYS Text Editor";

    public static final String FONTS[] = { "Serif", "SansSerif", "Courier" };
    protected Font fonts[];

    protected JTextArea testEditor;
    protected JMenuItem[] fontMenuItems;
    protected JCheckBoxMenuItem boldMenuItem;
    protected JCheckBoxMenuItem italicMenuItem;

    // component to choose File
    protected JFileChooser fileChooser;
    protected File currentFile;

    // component to show Toolbar
    protected JToolBar toolBar;
    protected JComboBox cbFonts;
    protected SmallToggleButton boldButton;
    protected SmallToggleButton italicButton;

    public Main() {
        super(APP_NAME);
        setSize(450, 350);

        fonts = new Font[FONTS.length];
        for (int k = 0; k < FONTS.length; k++)
            fonts[k] = new Font(FONTS[k], Font.PLAIN, 14);

        testEditor = new JTextArea();
        JScrollPane ps = new JScrollPane(testEditor);
        getContentPane().add(ps, BorderLayout.CENTER);

        testEditor.append("Start typing here.....");

        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        updateEditor();
    }

    protected JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        // File Menu creation
        JMenu mFile = new JMenu("File");
        // set mnemonics to File menu
        mFile.setMnemonic('f');

        ImageIcon iconNew = new ImageIcon("image/New.gif");
        Action actionNew = new AbstractAction("New", iconNew) {
            public void actionPerformed(ActionEvent e) {
                testEditor.setText("");
                currentFile = null;
                setTitle(APP_NAME + " [Untitled]");
            }
        };

        JMenuItem item = new JMenuItem(actionNew);
        item.setIcon(new ImageIcon("image/New.gif"));
        item.setMnemonic('n');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
        mFile.add(item);

        ImageIcon iconOpen = new ImageIcon("image/Open.gif");
        Action actionOpen = new AbstractAction("Open...", iconOpen) {
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(Main.this);
                repaint(); // It's amaizing, but the bug is still there...
                if (result != JFileChooser.APPROVE_OPTION)
                    return;
                File f = fileChooser.getSelectedFile();
                if (f == null || !f.isFile())
                    return;
                currentFile = f;
                try {
                    FileReader in = new FileReader(currentFile);
                    testEditor.read(in, null);
                    in.close();
                    setTitle(APP_NAME + " [" + currentFile.getName() + "]");
                } catch (IOException ex) {
                    showError(ex, "Error reading file " + currentFile);
                }
            }
        };

        item = new JMenuItem(actionOpen);
        item.setMnemonic('o');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        mFile.add(item);

        ImageIcon iconSave = new ImageIcon("image/Save.gif");
        Action actionSave = new AbstractAction("Save...", iconSave) {
            public void actionPerformed(ActionEvent e) {
                if (currentFile == null) {
                    int result = fileChooser
                            .showSaveDialog(Main.this);
                    repaint();
                    if (result != JFileChooser.APPROVE_OPTION)
                        return;
                    File f = fileChooser.getSelectedFile();
                    if (f == null)
                        return;
                    currentFile = f;
                    setTitle(APP_NAME + " [" + currentFile.getName() + "]");
                }

                try {
                    FileWriter out = new FileWriter(currentFile);
                    testEditor.write(out);
                    out.close();
                } catch (IOException ex) {
                    showError(ex, "Error saving file " + currentFile);
                }
            }
        };

        item = new JMenuItem(actionSave);
        item.setMnemonic('s');
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        mFile.add(item);

        mFile.addSeparator();

        Action actionExit = new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        item = new JMenuItem(actionExit);
        item.setMnemonic('x');
        mFile.add(item);
        menuBar.add(mFile);

        // Create toolbar
        toolBar = new JToolBar();
        JButton bNew = new SmallButton(actionNew, "New text");
        toolBar.add(bNew);
        JButton bOpen = new SmallButton(actionOpen, "Open text file");
        toolBar.add(bOpen);
        JButton bSave = new SmallButton(actionSave, "Save text file");
        toolBar.add(bSave);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        ActionListener fontListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEditor();
            }
        };

        JMenu mFont = new JMenu("Font");
        mFont.setMnemonic('o');

        ButtonGroup group = new ButtonGroup();
        fontMenuItems = new JMenuItem[FONTS.length];
        for (int k = 0; k < FONTS.length; k++) {
            int m = k + 1;
            fontMenuItems[k] = new JRadioButtonMenuItem(m + " " + FONTS[k]);
            fontMenuItems[k].setSelected(k == 0);
            fontMenuItems[k].setMnemonic('1' + k);
            fontMenuItems[k].setFont(fonts[k]);
            fontMenuItems[k].addActionListener(fontListener);
            group.add(fontMenuItems[k]);
            mFont.add(fontMenuItems[k]);
        }

        mFont.addSeparator();

        // Add combobox to tollbar
        toolBar.addSeparator();
        cbFonts = new JComboBox(FONTS);
        cbFonts.setMaximumSize(cbFonts.getPreferredSize());
        cbFonts.setToolTipText("Available fonts");
        ActionListener lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = cbFonts.getSelectedIndex();
                if (index < 0)
                    return;
                fontMenuItems[index].setSelected(true);
                updateEditor();
            }
        };
        cbFonts.addActionListener(lst);
        toolBar.add(cbFonts);

        toolBar.addSeparator();
        boldMenuItem = new JCheckBoxMenuItem("Bold");
        boldMenuItem.setMnemonic('b');
        Font fn = fonts[1].deriveFont(Font.BOLD);
        boldMenuItem.setFont(fn);
        boldMenuItem.setSelected(false);
        boldMenuItem.addActionListener(fontListener);
        mFont.add(boldMenuItem);

        italicMenuItem = new JCheckBoxMenuItem("Italic");
        italicMenuItem.setMnemonic('i');
        fn = fonts[1].deriveFont(Font.ITALIC);
        italicMenuItem.setFont(fn);
        italicMenuItem.setSelected(false);
        italicMenuItem.addActionListener(fontListener);
        mFont.add(italicMenuItem);

        menuBar.add(mFont);

        ImageIcon img1 = new ImageIcon("image/Bold.gif");
        boldButton = new SmallToggleButton(false, img1, img1, "Bold font");
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boldMenuItem.setSelected(boldButton.isSelected());
                updateEditor();
            }
        };
        boldButton.addActionListener(lst);
        toolBar.add(boldButton);

        img1 = new ImageIcon("image/Italic.gif");
        italicButton = new SmallToggleButton(false, img1, img1, "Italic font");
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                italicMenuItem.setSelected(italicButton.isSelected());
                updateEditor();
            }
        };
        italicButton.addActionListener(lst);
        toolBar.add(italicButton);

        // Add color selection menu
        JMenu mOpt = new JMenu("Options");
        mOpt.setMnemonic('p');

        ColorMenu cm = new ColorMenu("Foreground");
        cm.setColor(testEditor.getForeground());
        cm.setMnemonic('f');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ColorMenu m = (ColorMenu) e.getSource();
                testEditor.setForeground(m.getColor());
            }
        };
        cm.addActionListener(lst);
        mOpt.add(cm);

        cm = new ColorMenu("Background");
        cm.setColor(testEditor.getBackground());
        cm.setMnemonic('b');
        lst = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ColorMenu m = (ColorMenu) e.getSource();
                testEditor.setBackground(m.getColor());
            }
        };
        cm.addActionListener(lst);
        mOpt.add(cm);
        menuBar.add(mOpt);

        return menuBar;
    }

    protected void updateEditor() {
        int index = -1;
        for (int k = 0; k < fontMenuItems.length; k++) {
            if (fontMenuItems[k].isSelected()) {
                index = k;
                break;
            }
        }
        if (index == -1)
            return;

        if (index == 2) { // Courier
            boldMenuItem.setSelected(false);
            boldMenuItem.setEnabled(false);
            italicMenuItem.setSelected(false);
            italicMenuItem.setEnabled(false);
            boldButton.setSelected(false);
            boldButton.setEnabled(false);
            italicButton.setSelected(false);
            italicButton.setEnabled(false);
        } else {
            boldMenuItem.setEnabled(true);
            italicMenuItem.setEnabled(true);
            boldButton.setEnabled(true);
            italicButton.setEnabled(true);
        }

        // Synchronize toolbar and menu components
        cbFonts.setSelectedIndex(index);
        boolean isBold = boldMenuItem.isSelected();
        boolean isItalic = italicMenuItem.isSelected();
        if (boldButton.isSelected() != isBold)
            boldButton.setSelected(isBold);
        if (italicButton.isSelected() != isItalic)
            italicButton.setSelected(isItalic);

        int style = Font.PLAIN;
        if (boldMenuItem.isSelected())
            style |= Font.BOLD;
        if (italicMenuItem.isSelected())
            style |= Font.ITALIC;
        Font fn = fonts[index].deriveFont(style);
        testEditor.setFont(fn);
        testEditor.repaint();
    }

    public void showError(Exception ex, String message) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, message, APP_NAME,
                JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String argv[]) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class SmallButton extends JButton implements MouseListener {
    protected Border raisedBorder;
    protected Border loweredBorder;
    protected Border inactiveBorder;

    public SmallButton(Action act, String tip) {
        super((Icon) act.getValue(Action.SMALL_ICON));
        raisedBorder = new SoftBevelBorder(BevelBorder.RAISED);
        loweredBorder = new SoftBevelBorder(BevelBorder.LOWERED);
        inactiveBorder = new EmptyBorder(3, 3, 3, 3);
        setBorder(inactiveBorder);
        setMargin(new Insets(1, 1, 1, 1));
        setToolTipText(tip);
        addActionListener(act);
        addMouseListener(this);
        setRequestFocusEnabled(false);
    }

    public float getAlignmentY() {
        return 0.5f;
    }

    public void mousePressed(MouseEvent e) {
        setBorder(loweredBorder);
    }

    public void mouseReleased(MouseEvent e) {
        setBorder(inactiveBorder);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        setBorder(raisedBorder);
    }

    public void mouseExited(MouseEvent e) {
        setBorder(inactiveBorder);
    }
}

class SmallToggleButton extends JToggleButton implements ItemListener {

    protected Border raisedBorder;
    protected Border loweredBorder;

    public SmallToggleButton(boolean selected, ImageIcon imgUnselected,
                             ImageIcon imgSelected, String tip) {
        super(imgUnselected, selected);
        setHorizontalAlignment(CENTER);
        setBorderPainted(true);
        raisedBorder = new SoftBevelBorder(BevelBorder.RAISED);
        loweredBorder = new SoftBevelBorder(BevelBorder.LOWERED);
        setBorder(selected ? loweredBorder : raisedBorder);
        setMargin(new Insets(1, 1, 1, 1));
        setToolTipText(tip);
        setRequestFocusEnabled(false);
        setSelectedIcon(imgSelected);
        addItemListener(this);
    }

    public float getAlignmentY() {
        return 0.5f;
    }

    public void itemStateChanged(ItemEvent e) {
        setBorder(isSelected() ? loweredBorder : raisedBorder);
    }
}

// Custom menu component - NEW
class ColorMenu extends JMenu {

    protected Border unselectedBorder;
    protected Border selectedBorder;
    protected Border activeBorder;

    protected Hashtable panels;
    protected ColorPane selectedPanel;

    public ColorMenu(String name) {
        super(name);
        unselectedBorder = new CompoundBorder(new MatteBorder(1, 1, 1, 1,
                getBackground()), new BevelBorder(BevelBorder.LOWERED,
                Color.white, Color.gray));
        selectedBorder = new CompoundBorder(new MatteBorder(2, 2, 2, 2,
                Color.red), new MatteBorder(1, 1, 1, 1, getBackground()));
        activeBorder = new CompoundBorder(new MatteBorder(2, 2, 2, 2,
                Color.blue), new MatteBorder(1, 1, 1, 1, getBackground()));

        JPanel p = new JPanel();
        p.setBorder(new EmptyBorder(5, 5, 5, 5));
        p.setLayout(new GridLayout(8, 8));
        panels = new Hashtable();

        int[] values = new int[] { 0, 128, 192, 255 };
        for (int r = 0; r < values.length; r++) {
            for (int g = 0; g < values.length; g++) {
                for (int b = 0; b < values.length; b++) {
                    Color c = new Color(values[r], values[g], values[b]);
                    ColorPane pn = new ColorPane(c);
                    p.add(pn);
                    panels.put(c, pn);
                }
            }
        }
        add(p);
    }

    public void setColor(Color c) {
        Object obj = panels.get(c);
        if (obj == null)
            return;
        if (selectedPanel != null)
            selectedPanel.setSelected(false);
        selectedPanel = (ColorPane) obj;
        selectedPanel.setSelected(true);
    }

    public Color getColor() {
        if (selectedPanel == null)
            return null;
        return selectedPanel.getColor();
    }

    public void doSelection() {
        fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                getActionCommand()));
    }

    class ColorPane extends JPanel implements MouseListener {
        protected Color color;
        protected boolean isSelected;

        public ColorPane(Color c) {
            color = c;
            setBackground(c);
            setBorder(unselectedBorder);
            String msg = "R " + c.getRed() + ", G " + c.getGreen() + ", B "
                    + c.getBlue();
            setToolTipText(msg);
            addMouseListener(this);
        }

        public Color getColor() {
            return color;
        }

        public Dimension getPreferredSize() {
            return new Dimension(15, 15);
        }

        public Dimension getMaximumSize() {
            return getPreferredSize();
        }

        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
            if (isSelected)
                setBorder(selectedBorder);
            else
                setBorder(unselectedBorder);
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
            setColor(color);
            MenuSelectionManager.defaultManager().clearSelectedPath();
            doSelection();
        }

        public void mouseEntered(MouseEvent e) {
            setBorder(activeBorder);
        }

        public void mouseExited(MouseEvent e) {
            setBorder(isSelected ? selectedBorder : unselectedBorder);
        }
    }
}

