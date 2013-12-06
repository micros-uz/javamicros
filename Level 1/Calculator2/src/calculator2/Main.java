package calculator2;

import java.util.Scanner;

public class Main {
    private static Scanner _scanner;
    

    public static void main(String[] args) {
        print("Calculator 1.0 " + "\u00A9");
        print("Empty input is treated as exit");
        
        _scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        
        while(true){
            try{
                double n1 = getFirst();
                double n2 = getSecond();
                String op = getOp();
                double res = calculator.calc(n1, n2, op);
                print("Result = " + res);
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
        print("Enter second number:");
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

    
    
    
}
