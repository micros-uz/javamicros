package calculator;

import java.util.Scanner;

public class Main {
    private static Scanner _scanner;

    public static void main(String[] args) {
        print("Calculator 1.0 " + "\u00A9");
        print("Empty input is treated as exit");
        
        _scanner = new Scanner(System.in);
        
        while(true){
            try{
                double n1 = getFirst();
                double n2 = getSecond();
                String op = getOp();
                calc(n1, n2, op);
            }
            catch(NumberFormatException ex){
                print("GAME OVER");
                break;
            }
        }
    }

    private static double getFirst() {
        print("Enter first number:");
        return getInteger();
    }

    private static double getSecond() {
        print("Enter first number:");
        return getInteger();
    }

    @SuppressWarnings("empty-statement")
    private static String getOp() {
        print("Enter operaction:");
        String op;
        
        while(true){
            op = _scanner.nextLine();
            if ("+".equals(op) || "-".equals(op) ||
                    "*".equals(op) || "/".equals(op))
                break;
            else
                print("Wrong operation");
        }        
        
        return op;
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static double getInteger() {
        String с = _scanner.nextLine();
        
        return Double.parseDouble(с);
    }

    private static void calc(double n1, double n2, String op) {
        double res;
        
        switch(op){
            case "+":
                res = n1 + n2;
                break;
            case "-":
                res = n1 - n2;
                break;
            case "*":
                res = n1 * n2;
                break;
            case "/":
                res = n1 / n2;
                break;
            default: 
                return;
        }
        
        print("Result = " + res);
    }
    
    
}
