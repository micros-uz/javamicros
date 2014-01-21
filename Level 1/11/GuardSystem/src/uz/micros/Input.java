package uz.micros;

import java.util.Scanner;

public class Input {
    private static Scanner _scanner = new Scanner(System.in);

    public Input(){
        _scanner = new Scanner(System.in);
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public double getInteger() {
        String с = _scanner.nextLine();

        return Double.parseDouble(с);
    }

    public static String getLine(){
        return _scanner.nextLine();
    }
}
