package uz.micros;

import java.io.*;

public class Jvm {
    JvmListener listener;

    public void compile(File file) {
        if (file.exists())
            compileFile(file.getPath());
    }

    public void run() {

    }

    private void compileFile(String filePath){
        try {
            ProcessBuilder pb = new ProcessBuilder("c:\\Program Files\\Java\\jdk1.7.0_45\\bin\\javac.exe", filePath);
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            String line = null;
            while ((line = in.readLine()) != null) {
                listener.sendEvent(JvmEvent.Error, line);
            }

            listener.sendEvent(JvmEvent.Ok, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListener(JvmListener listener) {
        this.listener = listener;
    }
}
