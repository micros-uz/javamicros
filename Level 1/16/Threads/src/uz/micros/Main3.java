package uz.micros;

import static java.lang.System.out;

public class Main3 {
    private static int x;
    private static Object o = new Object();
    private static Object o1 = new Object();

    public static void main(String[] args){

        new Thread(){
            public void run(){
                while (true){

                    synchronized (o){
                        x++;
                    }
                }
            }
        }.start();

        new Thread(){
            public void run(){
                while (true){
                    synchronized(o1){
                        if (x%2 == 0) out.println(x);
                    }
                }
            }
        }.start();
    }
}
