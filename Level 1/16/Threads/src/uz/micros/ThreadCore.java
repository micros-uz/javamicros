package uz.micros;

import static java.lang.System.out;

public class ThreadCore implements Runnable {
    @Override
    public void run() {
        for (int k = 0; k < 1000; k++)
            out.print("-");
    }
}
