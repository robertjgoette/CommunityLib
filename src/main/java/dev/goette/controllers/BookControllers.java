package dev.goette.controllers;


import com.google.gson.Gson;
import dev.goette.entities.Book;
import dev.goette.exceptions.ResourceNotFound;
import dev.goette.services.BookService;
import io.javalin.http.Handler;

import java.util.List;

// Controllers are responsble for handaling icoming HTTP request
public class BookControllers {


    private BookService bookService;
    public BookControllers(BookService bookService){
        this.bookService = bookService;
    }
    //handler is a lambda that takes in one param ctx it is a context object
    //...?
    //...?
    public Handler getAllBooks = ctx ->{
        String title = ctx.queryParam("titlecontains"); // querys are for response you think you could get 0-many back
        List<Book> books;
        if(title != null){
            books = this.bookService.getBookByTitle(title);
        }else {
            books = this.bookService.retriveAllBooks();
        }
        Gson gson = new Gson();
        String bookJSON = gson.toJson(books);
        ctx.result(bookJSON);
        ctx.status(200);
        ctx.contentType("application/json");
    };
    public Handler getBookById = ctx ->{
      try {
          int id = Integer.parseInt(ctx.pathParam("id"));
          Book book = this.bookService.retriveBooksById(id);
          Gson gson = new Gson();
          String bookJson = gson.toJson(book);
          ctx.result(bookJson);
          ctx.status(200);
          ctx.contentType("application/json");
      }catch (ResourceNotFound resourceNotFound){
          ctx.result(resourceNotFound.getMessage());
          ctx.status(404);

      }
    };

    public Handler createBook = ctx->{
        Gson gson = new Gson();
        Book book = gson.fromJson(ctx.body(),Book.class); // Second arg is what class to make
        this.bookService.bookRegister(book);
        String bookJSON = gson.toJson(book);
        ctx.result(bookJSON);
        ctx.status(201);
    };

}
