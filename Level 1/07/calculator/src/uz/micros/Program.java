package uz.micros;

import javafx.util.Pair;

public class Program {

    public void run(String[] mas) {

        Input.print("Calculator 1.0 @ UCD MICROS");
        Calc c = new Calc();
        Params p;

        if (mas.length == 0){
            p = getConsoleParams();
        }
        else{
            p = getStartParams(mas);
        }

        Input.print("Result: " + c.calc(p));
    }

    private Params getStartParams(String[] mas) {
        Params p = new Params();
        p.n1 = Double.valueOf(mas[0]);
        p.n2 = Double.valueOf(mas[1]);
        p.op = mas[2].charAt(0);

        return p;
    }

    private Pair<Boolean, Double> readNumber(String s){
        try {
            Input.print(s);
            return new Pair<Boolean, Double>(true, Input.getNumber());
        } catch (CalcException e) {
            return new Pair<Boolean, Double>(false, 0.0);
        }
    }

    private Params getConsoleParams() {
        Params p = new Params();
        Pair<Boolean, Double> pair;

        do{
            pair = readNumber("Enter first number: ");
            p.n1 = pair.getValue();
        }while(!pair.getKey());

        do{
            pair = readNumber("Enter second number: ");
            p.n2 = pair.getValue();
        }while(!pair.getKey());

        Input.print("Enter operation: ");
        p.op = Input.getChar();

        return p;
    }
}
