package uz.micros;

import javax.swing.*;

//import javax.swing.filechooser.FileFilter;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class Editor {

    private final String JAVA_DESC = "Java Files (*.java)";
    private final String TEXT_DESC = "Text Files (*.txt)";
    private final String JAVA_EXT = ".java";
    private final String TEXT_EXT = ".txt";

    private final JTextArea textArea;
    private final Component parent;
    private File curFile;
    private List<FileChangedListener> listeners;

    public Editor(JTextArea ta, Component comp) {
        textArea = ta;
        parent = comp;
    }

    public void open(String file) {
        curFile = new File(file);
        openInternal();
    }

    public void open() {
        JFileChooser dlg = getFileChooser();

        if (dlg.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            curFile = dlg.getSelectedFile();

            openInternal();
        }
    }

    private void openInternal() {
        try {
            FileReader reader = new FileReader(curFile);
            textArea.read(reader, null);
            reader.close();

            notifyFileChanged();

        } catch (FileNotFoundException e) {
            curFile = null;
        } catch (IOException e) {
            curFile = null;
        }
    }

    private void notifyFileChanged() {
        for (FileChangedListener fcl : listeners)
            fcl.fileChanged(curFile.getAbsolutePath());
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
                    // todo -
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
            FileFilter filter = dlg.getFileFilter();
            String ext = filter.getDescription().equals(JAVA_DESC)
                    ? JAVA_EXT : TEXT_EXT;

            File file = dlg.getSelectedFile();

            curFile = file.getAbsolutePath().endsWith(ext)
                    ? file : new File(file + ext);

            save();
        }
    }

    private JFileChooser getFileChooser() {
        JFileChooser dlg = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(JAVA_DESC, "java");
        dlg.setFileFilter(filter);
        dlg.addChoosableFileFilter(new FileNameExtensionFilter(TEXT_DESC, "txt"));

        return dlg;
    }

    public void addFileChangedListener(FileChangedListener listener) {
        if (listeners == null)
            listeners = new ArrayList<FileChangedListener>();

        listeners.add(listener);
    }

    public File getFile() {
        return curFile;
    }

    public boolean hasJavaFile() {
        return curFile != null && curFile.getName().endsWith(JAVA_EXT);
    }
}
