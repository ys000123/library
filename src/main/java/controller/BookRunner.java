package controller;

import config.ConstructInjector;
import domain.Book;
import java.util.Arrays;
import java.util.List;
import service.BookService;

public enum BookRunner {
    입고(1){
        @Override
        public void exec() {
            System.out.println("\n[입고] 제목, 저자, ISBN, 수량을 입력하세요");
            String title = Input.inputString("제목 : ");
            String author = Input.inputString("저자 : ");
            String isbn = Input.inputString("ISBN : ");
            int quantity = Input.inputInt("수량 : ");
            BookService bookService = ConstructInjector.bookService();
            bookService.store(title, author, isbn, quantity);
        }
    },
    출고(2){
        @Override
        public void exec() {
            System.out.println("\n[출고] 도서 번호 및 수량를 입력하세요");
            Long bookId = Input.inputLong("도서 번호 : ");
            int quantity = Input.inputInt("출고 수량 : ");
            BookService bookService = ConstructInjector.bookService();
            bookService.release(bookId, quantity);
        }
    },
    목록(3){
        @Override
        public void exec() {
            BookService bookService = ConstructInjector.bookService();
            List<Book> response = bookService.findAll();
            Output.printAll(response);
        }
    },
    검색(4){
        @Override
        public void exec() {
            System.out.println("\n[검색] 저자명을 입력해주세요");
            String author = Input.inputString("저자 : ");

            BookService bookService = ConstructInjector.bookService();
            List<Book> response = bookService.findAllByCondition(author);
            Output.printAll(response);
        }
    },
    종료(5){
        @Override
        public void exec() {
            System.out.println("종료되었습니다");
        }
    };
    private final int index;
    BookRunner(int index) {
        this.index = index;
    }

    public static BookRunner findByIndex(int index) {
        return Arrays.stream(values())
                .filter(e -> e.index == index)
                .findFirst()
                .orElseThrow();
    }

    public abstract void exec();
}
