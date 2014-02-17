package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class Library implements Iterable<Book> {
    private ArrayList<Book[]> books = new ArrayList<Book[]>();

    public void add(Book... book) {
        if (book.length > 0) {
            books.add(book);
        }
    }

    @Override
    public Iterator<Book> iterator() {
        return new LibIterator(books);
    }

}

