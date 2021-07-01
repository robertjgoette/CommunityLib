package dev.goette.daos;

import dev.goette.entities.Book;
import dev.goette.exceptions.ResourceNotFound;

import java.util.List;

//proper interface for our dao
public interface BookDao {

    //CRUD
    //CREATE
    Book createBook(Book book);

    //READ
    Book getBookById(int id) throws ResourceNotFound;
    List<Book> getAllBooks();

    // UPDATE
    Book updateBook(Book book);

    //DELETE
    boolean deleteBookById(int id);
}
