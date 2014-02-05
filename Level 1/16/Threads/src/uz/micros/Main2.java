package uz.micros;

import static java.lang.System.out;

public class Main2 {
    public static void main(String[] args){
        new Thread1("+").start();

        new Thread(new ThreadCore()).start();

        new Thread(){
            public void run() {
                for (int k = 0; k < 1000; k++)
                    out.print("z");

            }
        }.start();
    }
}
