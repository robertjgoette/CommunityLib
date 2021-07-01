package dev.goette.servicetests;


import dev.goette.daos.BookDao;
import dev.goette.entities.Book;
import dev.goette.services.BookService;
import dev.goette.services.BookServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ServiceTest {

    @Mock
    BookDao bookDao = Mockito.mock(BookDao.class);

    BookService bookService = new BookServiceImpl(bookDao);

    @BeforeMethod
    public void init() {
        List<Book> testBooks = new ArrayList<>();
        Book book1 = new Book(0, "A stitch", "", true, 1, 0);
        Book book2 = new Book(0, "Z stitch", "", true, 1, 0);
        Book book3 = new Book(0, "C stitch", "", true, 1, 0);
        testBooks.add(book1);
        testBooks.add(book2);
        testBooks.add(book3);
    }


    @Test
    public void findByTitle(){
        List<Book> books = this.bookService.getBookByTitle("A stitch");
        Assert.assertEquals(books.size(),1);
        Assert.assertEquals(books.get(0).getTitle(), "A stitch");
    }
}
