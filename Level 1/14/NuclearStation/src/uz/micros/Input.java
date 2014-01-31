package uz.micros;

import java.util.Scanner;

public class Input {
    private static Scanner s = new Scanner(System.in);

    public static void print(String msg){
        System.out.println(msg);
    }

    public static int getNumber() {
        String st = s.nextLine();
        return Integer.valueOf(st);
    }
}
