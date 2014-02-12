package com.company;

import static java.lang.System.out;
//import java.lang.Object;

class Object2 extends Object{
    void show(){out.println(toString());}
}

class Object3 extends Object2{
    public String toString() {
        return "NEW Object3";
    }
};

public class Main {
    public static void main(String[] args) {

        Object o = new Object();
        Object2 o2 = new Object2();
        Object3 o3 = new Object3();

        out.println(o.toString());
        o2.show();
        o2 = o3;
        o2.show();
    }
}
