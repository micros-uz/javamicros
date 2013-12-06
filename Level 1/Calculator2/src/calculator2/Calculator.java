package calculator2;

public class Calculator {

    public double calc(double n1, double n2, String op) {
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
                throw new IllegalArgumentException();
        }
        
        return res;
    }
}
