package dev.goette.daotests;


import dev.goette.daos.BookDao;
import dev.goette.daos.BookDaoLocal;
import dev.goette.daos.BookDaoPostgres;
import dev.goette.entities.Book;
import dev.goette.exceptions.ResourceNotFound;
import javafx.scene.layout.Priority;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BookDaoTests {
    // Teat to the interface
    static BookDao bookDao = new BookDaoPostgres();
    static Book testBook = new Book(0, "Dracula", "Bram Stoker", true, 1,0);

    @Test(priority = 1)
    void createBook(){
        Book book = bookDao.createBook(testBook);

        // Java does have an assert method
        //assert book.getBookId() != 0;  assert isn't common though

        // More common and better practice
        Assert.assertNotEquals(book.getBookId(),0);
    }
    @Test(priority = 2)
    void getBookId() throws ResourceNotFound {
        Book book = bookDao.getBookById(testBook.getBookId());
        Assert.assertEquals(book.getTitle(), "Dracula");
    }

    @Test(priority = 3)
    void updateBook() throws ResourceNotFound {
        testBook.setAvailable(false);
        bookDao.updateBook(testBook);

        Book book = bookDao.getBookById(testBook.getBookId());
        Assert.assertFalse(book.isAvailable());
    }

    @Test(priority = 4)
    void deleteBook(){
        boolean result = bookDao.deleteBookById(testBook.getBookId());
        Assert.assertTrue(result);
    }

    @Test(priority = 5, dependsOnMethods = "createBook")// intagration test because it realise on create book
    //...?
    void allBooks(){
        Book book1 = new Book(0,"Frankenstine1", "Merry Shelly", true, 1, 0);
        Book book2 = new Book(0,"Frankenstine2", "Merry Shelly", true, 1, 0);
        Book book3 = new Book(0,"Frankenstine3", "Merry Shelly", true, 1, 0);
        bookDao.createBook(book1);
        bookDao.createBook(book2);
        bookDao.createBook(book3);
        List<Book> books = bookDao.getAllBooks();
        Assert.assertTrue(books.size()>=3);
        bookDao.deleteBookById(book1.getBookId());
        bookDao.deleteBookById(book2.getBookId());
        bookDao.deleteBookById(book3.getBookId());
    }

}
