package config;

import respository.BookRepository;
import respository.BookRepositoryImpl;
import service.BookService;

public class ConstructInjector {

    public static BookService bookService(){
        return new BookService(bookRepository());
    }

    public static BookRepository bookRepository(){
        return new BookRepositoryImpl();
    }
}
