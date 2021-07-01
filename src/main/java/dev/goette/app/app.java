package dev.goette.app;

import dev.goette.controllers.BookControllers;
import dev.goette.daos.BookDao;
import dev.goette.daos.BookDaoLocal;
import dev.goette.daos.BookDaoPostgres;
import dev.goette.services.BookService;
import dev.goette.services.BookServiceImpl;
import io.javalin.Javalin;

import javax.sound.sampled.BooleanControl;

public class app {
    public static void main(String[] args) {
        Javalin.create(config -> {
            config.requestLogger((ctx, ms) -> {
                config.enableCorsForAllOrigins();
                config.enableDevLogging();
            });
        });
        Javalin app = Javalin.create();
        BookDao bookDao = new BookDaoPostgres();
        BookService bookService = new BookServiceImpl(bookDao);
        BookControllers bookControllers = new BookControllers(bookService);

        // get / books
        app.get("/books",bookControllers.getAllBooks);//The handler is the function to be exicuted when a request is sent to that uri

        // get book/5
        app.get("/books/:id",bookControllers.getBookById);

        //
        app.post("/books",bookControllers.createBook);

        //
        //app.put("/books/:id",null);

        //
        //app.delete("/books/:id",null);

        //
        //app.patch("/books/:id",null);

        //
        //app.patch("/checking/:id",null);

        //
        //app.patch("/checkout/:id", null);



        app.start();// defaults to port 7000
    }
}
