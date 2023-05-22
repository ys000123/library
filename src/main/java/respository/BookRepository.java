package respository;

import domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);  // 입고

    boolean existByIsbn(String isbn);
    void release(Book book); // 출고

    Optional<Book> findById(Long bookId);
    List<Book> findAll();  // 책을 조회 (전체)
    List<Book> findAllByCondition(String author);  // 책을 조회(저자명으로 조회)
}
