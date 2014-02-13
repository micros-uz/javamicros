package uz.micros;

import java.io.File;

public class Main {

    public static void main(String... args) {
        File[] files = new File("C:/").listFiles();
        showFiles(files);
    }

    public static void showFiles(File[] files) {
        if (files != null)
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.printf("D: %70s\n", file.getName());
                    showFiles(file.listFiles()); // Calls same method again.
                } else {
                    System.out.printf("F: %70s\n", file.getName());
                }
            }
    }
}
