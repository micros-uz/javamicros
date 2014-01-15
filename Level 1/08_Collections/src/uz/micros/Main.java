package uz.micros;

import com.sun.javafx.collections.SortableList;
import com.sun.javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main {

    private static void pb(int n){
        String s = Integer.toBinaryString(n);

        while (s.length() < 8)
            s = "0" + s;

        System.out.println(s);
    }

    public static void main2(String[] args) {

        int n = 0xAABBCCDD;

        long m = 0x1122334455667788L;

        //m = n;

        n = (int)(m >> 32);

        System.out.println(Integer.toHexString((int) (m >> 32)));
        System.out.println(Integer.toHexString(n));

        byte b = 7;
        pb(b);

        pb(b << 1);
        pb(b << 1 >> 1);

        byte r = (byte)(b >> 1);
        pb(r);

        pb(6 | 1);
        pb(6 | 8);

        pb(6 & 3);
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add(123);
        list.add(123123.2);
        list.add("123123.2");

        for(Object o : list)
            System.out.println(o);

        ArrayList<Integer> list2 = new ArrayList<Integer>();

        list2.add(222);
        list2.add(87987987);

        for (int k = 0; k < list2.size(); k++){
            System.out.println("list2[" + (k+1) + "] = " + list2.get(k));
        }

        Stack<Integer> s = new Stack<Integer>();

        s.add(22);s.add(44);s.add(77);s.add(88);s.add(99);

        int n = s.pop();
        System.out.println(n);
        System.out.println(s);
        n = s.peek();
        System.out.println(n);
        System.out.println(s);


        PriorityQueue<Integer> q = new PriorityQueue<Integer>();

        q.add(22);q.add(44);q.add(77);q.add(88);q.add(99);

        System.out.println(q);
        n = q.poll();
        System.out.println(n);
        System.out.println(q);
        n = q.remove();
        System.out.println(n);
        System.out.println(q);
    }
}
