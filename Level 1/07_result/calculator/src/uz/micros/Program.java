package uz.micros;

import javafx.util.Pair;

public class Program {

    public Program(int r) {
        Input.print("From CONSTRUCTOR!!!");
    }

    public void run(String[] mas) {

        Input.print("Calculator 1.0 @ UCD MICROS");
        Calc c = new Calc();
        Params p;

        if (mas.length == 0) {
            p = getConsoleParams();
        } else {
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

    private Params getConsoleParams() {
        Params p = new Params();
        Result<Boolean, Double> pair;

        do{
            pair = readNumber("Enter first number: ");
        }while(!pair.getFlag());

        p.n1 = pair.getRes();

        do{
            pair = readNumber("Enter second number: ");
        }while(!pair.getFlag());

        p.n2 = pair.getRes();

        Input.print("Enter operation: ");
        p.op = Input.getChar();

        return p;
    }

    private Result<Boolean, Double> readNumber(String s) {
        try{
            Input.print(s);
            return new Result<Boolean, Double>(true, Input.getNumber());
        }
        catch(CalcException e){
            return new Result<Boolean, Double>(false, 0.0);
        }
    }
}
