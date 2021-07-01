package dev.goette.services;

import dev.goette.entities.Book;
import dev.goette.exceptions.ResourceNotFound;

import java.util.List;

public interface BookService {

    Book bookRegister(Book book);
    List<Book> retriveAllBooks();
    Book retriveBooksById(int id) throws ResourceNotFound;
    List<Book> getBookByTitle(String title);
    Book updateBook(Book book);
    boolean decommisionBookById(int id);
    Book checkinById(int id) throws ResourceNotFound;
    Book checkOutById(int id) throws ResourceNotFound;

}
