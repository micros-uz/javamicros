package uz.micros;

import static java.lang.System.out;

public class Main {

    private static Object o = new Object();
    private static int x;
    private static boolean b;

    public static void main(String[] args) {

        new Thread()
        {
            @Override
            public void run() {


                while (true)
                {
                    synchronized (o){x++;}
                    //Thread.yield();

                }
/*                while(true){
                    if (b){
                        b = false;
                        out.println(x++);
                    }
                    Thread.yield();
*//*                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {}*//*
                }*/
            }
        }.start();

        new Thread()
        {
            @Override
            public void run() {


                while (true)
                {
                    synchronized (o){
                    if (x%2 == 0)
                        System.out.println("x=" + x);
                    }
                    //Thread.yield();
                }

/*                while(true){
                    if (!b){
                        b = true;
                        out.println(x--);
                    }
                    Thread.yield();
*//*                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {}*//*
                }*/
            }
        }.start();
    }
}
