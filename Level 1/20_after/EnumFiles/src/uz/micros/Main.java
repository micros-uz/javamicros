package uz.micros;

import java.io.File;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        File f = new File("C:/test");

        File[] files = f.listFiles();

        list(files);
    }

    private static void list(File[] files) {
        if (files != null)
            for (File f : files){
                System.out.printf("%s %70s % 8d\n",
                        f.isDirectory() ? "D:" : "F:",
                        f.getAbsolutePath(),
                        f.length());

                if (f.isDirectory())
                    list(f.listFiles());
            }
    }
}
