package uz.micros;

import static java.lang.System.out;

public class Thread1 extends Thread {
    private final String ss;

    public Thread1(String s) {
        ss = s;
    }

    @Override
    public void run() {
        for (int k = 0; k < 1000; k++)
            out.print(ss);
    }
}
