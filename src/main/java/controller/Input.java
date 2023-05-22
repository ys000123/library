package controller;

import java.util.Scanner;

public class Input {

    public static String inputString(String text) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print(text);
            return sc.next();
        } catch (RuntimeException ignored) {
            return inputString(text);
        }
    }


    public static Long inputLong(String text) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print(text);
            return sc.nextLong();
        } catch (RuntimeException ignored) {
            return inputLong(text);
        }
    }

    public static int inputInt(String text) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print(text);
            return sc.nextInt();
        } catch (RuntimeException ignored) {
            return inputInt(text);
        }
    }
}
