package uz.micros;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Editor {

    private final String JAVA_DESC = "Java files (*.java)";
    private final String JAVA_EXT = ".java";
    private final String TEXT_EXT = ".txt";

    private final JTextArea textArea;
    private final Component parent;
    private File curFile;
    private java.util.List<FileChangedListener> fileChangedListeners;

    public Editor(JTextArea ta, Component comp) {
        textArea = ta;
        parent = comp;
    }

    public void open() {
        JFileChooser dlg = getFileChooser();

        if (dlg.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            curFile = dlg.getSelectedFile();

            openCurFile();
        }
    }

    private void openCurFile() {
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
            String ext = filter.getDescription().equals(JAVA_DESC) ? JAVA_EXT :TEXT_EXT;

            curFile = new File(dlg.getSelectedFile() + ext);

            notifyFileChanged();

            save();

        }
    }

    private void notifyFileChanged() {
        for(FileChangedListener listener: fileChangedListeners)
            listener.fileChanged(curFile.getAbsolutePath(), curFile.length());
    }

    private JFileChooser getFileChooser() {
        JFileChooser dlg = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        dlg.setFileFilter(filter);
        dlg.addChoosableFileFilter(new FileNameExtensionFilter(JAVA_DESC, "java"));

        return dlg;
    }

    public boolean hasJavaFile() {
        return curFile != null && curFile.getName().contains(".java");
    }

    public File getFile() {
        return curFile;
    }

    public void addFileChangedListener(FileChangedListener listener) {
        if (fileChangedListeners == null)
            fileChangedListeners = new ArrayList<FileChangedListener>();

        fileChangedListeners.add(listener);
    }

    public void open(String path) {
        curFile = new File(path);
        openCurFile();
    }
}
