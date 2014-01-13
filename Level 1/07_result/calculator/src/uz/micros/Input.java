package uz.micros;

import java.util.Scanner;

public class Input {

    public static int a;
    private static Scanner s = new Scanner(System.in);

    public static void  print(String msg){
        System.out.println(msg);
    }

    public static double getNumber() throws CalcException{
        String st = s.nextLine();

        try{
            return Double.valueOf(st);
        }
        catch(NumberFormatException e){
            throw new CalcException();
        }
    }

    public static char getChar() {
        return s.nextLine().charAt(0);
    }
}
