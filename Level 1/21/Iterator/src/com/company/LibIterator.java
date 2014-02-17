package com.company;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

public class LibIterator implements Iterator<Book> {
    private final ArrayList<Book[]> books;
    private int curLine;
    private int curLineIdx;

    public LibIterator(ArrayList<Book[]> books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return curLine < books.size() &&
                books.get(curLine).length > curLineIdx;
    }

    @Override
    public Book next() {
        Object o;
        Book res = books.get(curLine)[curLineIdx];

/*        try {
            o =Book.class.getMethods()[0].invoke(res);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/

        if (++curLineIdx >= books.get(curLine).length){
            curLine++;
            curLineIdx = 0;
        }

        res.setLine(curLine);
        return res;
    }

    @Override
    public void remove() {
    }
}
