package service;

import com.sun.jdi.request.DuplicateRequestException;
import domain.Book;
import java.util.List;
import respository.BookRepository;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void store(String title, String author, String isbn, int quantity) {
        duplicateByIsbn(isbn);
        bookRepository.save(Book.create(title, author, isbn, quantity));
    }

    private void duplicateByIsbn(String isbn) {
        if (bookRepository.existByIsbn(isbn)) {
            throw new DuplicateRequestException();
        }
    }

    public void release(Long bookId, int quantity) {
        Book findBook = findBook(bookId);
        findBook.decreaseQuantity(quantity);
        bookRepository.release(findBook);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public List<Book> findAllByCondition(String author) {
        return bookRepository.findAllByCondition(author);
    }

    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }
}
