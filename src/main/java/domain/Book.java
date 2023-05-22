package domain;

import java.util.Objects;

public class Book {
    public static final int MAX_LENGNTH = 50;
    private Long bookId;
    private String title;
    private String author;
    private String ISBN;
    private int quantity;

    public Book(Long bookId, String title, String author, String ISBN, int quantity) {
        this.bookId = bookId;
        this.title = validMaxLength(title);
        this.author = validMaxLength(author);
        this.ISBN = validMaxLength(ISBN);
        this.quantity = quantity;
    }

    public static Book create(String title, String author, String isbn, int quantity) {
        return new Book(null, title, author, isbn, quantity);
    }
    
    private String validMaxLength(String str){
        if (Objects.nonNull(str) && str.length() <= MAX_LENGNTH){
            return str;
        }
        throw new IllegalArgumentException();
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int quantity){
        if (this.quantity < quantity) {
            throw new IllegalArgumentException();
        }
        this.quantity = this.quantity - quantity;
    }
}
