package uz.micros;

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

    private boolean readNumber(String s, Params p){
        try {
            Input.print(s);
            p.n1 = Input.getNumber();

            return true;
        } catch (CalcException e) {
            return false;
        }
    }

    private Params getConsoleParams() {
        Params p = new Params();

        readNumber("Enter first number: ", p);

        Input.print("Enter second number: ");
        p.n2 = Input.getNumber();
        Input.print("Enter operation: ");
        p.op = Input.getChar();

        return p;
    }
}
