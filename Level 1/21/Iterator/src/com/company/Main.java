package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> mas = new ArrayList<String>(
                Arrays.asList("ffff", "werwerwer"));

        for(String s : mas)
            System.out.println(s);

        Iterator<String> it = mas.iterator();

        while(it.hasNext())
            System.out.println(it.next());



        Library lib = new Library();

        lib.add();
        lib.add(new Book("111"), new Book("222"));
        lib.add(new Book("xxx"), new Book("yyy"), new Book("aaaaa"));
        lib.add(new Book("ssss"));

        for(Book b: lib)
            System.out.println(b);

        Iterator<Book> i= lib.iterator();
        while(i.hasNext()){
            Book b = i.next();
            System.out.println(b);
        }
    }
}
