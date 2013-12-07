package calculator2;

import consolehelpers.Input;
import java.util.Scanner;

public class Main {
    private static Scanner _scanner;
    private static Input _input;

    public static void main(String[] args) {
        _input = new Input();
        
        _input.print("Calculator 1.0 " + "\u00A9");
        _input.print("Empty input is treated as exit");

        Calculator calculator = new Calculator();
        
        while(true){
            try{
                double n1 = getFirst();
                double n2 = getSecond();
                String op = getOp();
                double res = calculator.calc(n1, n2, op);
                _input.print("Result = " + res);
            }
            catch(NumberFormatException ex){
                _input.print("GAME OVER");
                break;
            }
        }
    }

    private static double getFirst() {
        _input.print("Enter first number:");
        return _input.getInteger();
    }

    private static double getSecond() {
        _input.print("Enter second number:");
        return _input.getInteger();
    }

    @SuppressWarnings("empty-statement")
    private static String getOp() {
        _input.print("Enter operaction:");
        String op;
        
        while(true){
            op = _scanner.nextLine();
            if ("+".equals(op) || "-".equals(op) ||
                    "*".equals(op) || "/".equals(op))
                break;
            else
                _input.print("Wrong operation");
        }        
        
        return op;
    }



    
    
    
}
