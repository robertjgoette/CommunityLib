package dev.goette.daos;

import dev.goette.entities.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoLocal implements BookDao {

    private static Map<Integer,Book> bookTable = new HashMap<>();
    private static int idMaker = 0;

    @Override
    public Book createBook(Book book) {
        int key = ++BookDaoLocal.idMaker;
        book.setBookId(key);
        BookDaoLocal.bookTable.put(key,book);
        return book;
    }

    @Override
    public Book getBookById(int id) {
        return BookDaoLocal.bookTable.get(id);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>(BookDaoLocal.bookTable.values());
        return books;
    }

    @Override
    public Book updateBook(Book book) {
        BookDaoLocal.bookTable.put(book.getBookId(), book);
        return book;
    }

    @Override
    public boolean deleteBookById(int id) {
        Book book = BookDaoLocal.bookTable.remove(id);
        return book != null;

    }
}
