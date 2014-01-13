package uz.micros;


class A{
    public static int x;
}

public class Main {

    public static void main(String[] args) {

        if (args.length == 0 || args.length == 3)
            new Program(34).run(args);
        else
            Input.print("usage: <program> [number1 number2 operation]");
    }
}





