import controller.BookRunner;
import controller.Input;

public class Application {
    public static void main(String[] args) {
        while (true) {
            System.out.println("----------------------------------------------");
            System.out.println("[1] 입고  [2] 출고 [3] 전체 목록 [4] 저자명으로 검색 [5] 종료");
            int index = Input.inputInt("[번호를 입력하세요] : ");
            BookRunner runner = BookRunner.findByIndex(index);
            if (runner == BookRunner.종료){
                break;
            }
            runner.exec();
        }
    }
}
