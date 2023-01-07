package encryptdecrypt;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static int key;

    private static String text;

    public static void main(String[] args) {
        text = input();
        key = Integer.parseInt(input());
        encrypt();

    }

    private static String encrypt() {
        StringBuilder decrypt = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                decrypt.append((char) (97 - text.charAt(i) + 122));
            } else {
                decrypt.append(text.charAt(i));
            }
        }
        return decrypt.toString();
    }

    private static String input() {
        return scanner.nextLine();
    }
}
