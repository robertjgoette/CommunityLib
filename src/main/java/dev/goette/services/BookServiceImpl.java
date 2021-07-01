package dev.goette.services;

import dev.goette.daos.BookDao;
import dev.goette.entities.Book;
import dev.goette.exceptions.ResourceNotFound;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService{

    private BookDao bookDao = null;

    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public Book bookRegister(Book book) {
        return this.bookDao.createBook(book);
    }

    @Override
    public List<Book> retriveAllBooks() {
        return this.bookDao.getAllBooks();
    }

    @Override
    public Book retriveBooksById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        List<Book> books = this.bookDao.getAllBooks();
        List<Book> filteredBooks = new ArrayList<>();
        for(Book b : books){
            System.out.println(title);
            System.out.println(b.getTitle());
            if(b.getTitle().contains(title)){
                filteredBooks.add(b);
            }
        }
        return filteredBooks;
    }

    @Override
    public Book updateBook(Book book) {
        return this.bookDao.updateBook(book);
    }

    @Override
    public boolean decommisionBookById(int id) {
        return this.bookDao.deleteBookById(id);
    }

    @Override
    public Book checkinById(int id){
        Book book = this.bookDao.getBookById(id);
        book.setAvailable(true);
        book.setReturnDate(0);
        this.bookDao.updateBook(book);
        return book;
    }

    @Override
    public Book checkOutById(int id) {
        Book book = this.bookDao.getBookById(id);
        book.setAvailable(false);
        book.setReturnDate(System.currentTimeMillis()+1_209_600);
        this.bookDao.updateBook(book);
        return book;
    }
}
