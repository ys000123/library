package respository;

import domain.Book;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookRepositoryImplTest {
    @Test
    void test() throws SQLException {
        BookRepository bookRepository = new BookRepositoryImpl();
        Book book = new Book(null, "title", "authore", "isbm", 0);
        Book book2 = new Book(null, "title", "authore1", "isbm1", 0);
        Book book1 = new Book(null, "title", "authore2", "isbm2", 0);
        bookRepository.save(book);
        bookRepository.save(book2);
        bookRepository.save(book1);
    }

    @Test
    void findAllTest() throws SQLException {
        //given
        BookRepository bookRepository = new BookRepositoryImpl();

        //when
        var response = bookRepository.findAll();

        //then
        Assertions.assertEquals(response.size(), 3);
    }

    @Test
    void findAllConditionTest() throws SQLException {
        //given
        BookRepository bookRepository = new BookRepositoryImpl();

        //when
        var response = bookRepository.findAllByCondition("a");

        //then
        for (Book book : response) {
            System.out.println(book.getISBN());
        }
    }
}