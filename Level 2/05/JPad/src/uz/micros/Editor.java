package uz.micros;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class Editor {

    private final JTextArea textArea;
    private final Component parent;
    private File curFile;

    public Editor(JTextArea ta, Component comp) {
        textArea = ta;
        parent = comp;
    }

    public void open() {
        JFileChooser dlg = getFileChooser();

        if (dlg.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            curFile = dlg.getSelectedFile();

            try {
                FileReader reader = new FileReader(curFile);
                textArea.read(reader, null);
                reader.close();
            } catch (FileNotFoundException e) {
                curFile = null;
            } catch (IOException e) {
                curFile = null;
            }
        }
    }

    public void save() {
        if (curFile != null) {
            try {
                FileWriter writer = new FileWriter(curFile);
                textArea.write(writer);
                writer.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parent, "Couldn't write file " +
                        e.getMessage());
            }
        } else {
            saveAs();
        }
    }

    public void create() {

        if (textArea.getText().length() > 0) {
            int n = JOptionPane.showConfirmDialog(parent,
                    "Do you want to save changes?", "JPad",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            switch (n) {
                case JOptionPane.CANCEL_OPTION:
                    return;
                case JOptionPane.YES_OPTION:

                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }

        curFile = null;
        textArea.setText("");
    }

    public void saveAs() {
        JFileChooser dlg = getFileChooser();
        dlg.setSelectedFile(curFile);
        dlg.setDialogTitle("Save As");

        if (dlg.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            curFile = dlg.getSelectedFile();
            save();
        }
    }

    private JFileChooser getFileChooser() {
        JFileChooser dlg = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "log", "java");
        dlg.setFileFilter(filter);
        return dlg;
    }
}
