package uz.micros;

import javax.print.DocFlavor;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Jvm {
    private JvmListener listener;

    public void compile(File file) {
        if (file.exists())
        {
            compileFile(file.getPath());
        }
    }

    private void compileFile(String path) {
        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Java\\jdk1.7.0_45\\bin\\javac", path);
        pb.redirectErrorStream(true);
        try {
            listener.send(JvmEvent.Start, getTime());
            Process p = pb.start();

            InputStream stream = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = null;
            while((line = bufferedReader.readLine()) != null){
                listener.send(JvmEvent.Error, line);
            }

            listener.send(JvmEvent.End, getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

    }

    public void addListener(JvmListener jvmListener) {
        listener = jvmListener;
    }

    private String getTime(){
        return new SimpleDateFormat("HH:mm:ss.S")
                .format(new Date());
    }
}
