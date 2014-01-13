package uz.micros;

public class Calc {

    public double calc(Params p){

        double res = 0;

        switch (p.op){
            case '+':
                res = p.n1 + p.n2;
                break;
            case '-':
                res = p.n1 - p.n2;
                break;
            case '*':
                res = p.n1 * p.n2;
                break;
            case '/':
                res = p.n1 / p.n2;
                break;
            default:
                Input.print("Wrong operation");
                break;

        }
        return res;
    }
}
