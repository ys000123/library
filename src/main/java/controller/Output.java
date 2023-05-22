package controller;

import domain.Book;
import java.util.List;

public class Output {
    public static void printAll(List<Book> books) {
        System.out.println("도서번호   제목    저자   ISBN   수량");
        for (Book book : books) {
            String text = String.format("%d   %s   %s   %s   %d",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getISBN(),
                    book.getQuantity());
            System.out.println(text);
        }
    }
}
