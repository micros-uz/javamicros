package com.company;

public class Book {
    private final String name;
    private int line;

    public Book(String s){
        name = s;
    }

    @Override
    public String toString() {
        return name + " " + line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }
}
