package encryptdecrypt;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static int key;

    private static String text;

    public static void main(String[] args) {
        text = input();
        key = Integer.parseInt(input());
        System.out.println(encrypt());

    }

    private static String encrypt() {
        StringBuilder decrypt = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                char letter = (char) (text.charAt(i) + key);
                if (letter > 122) {
                    letter -= 26;
                }
                decrypt.append(letter);
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
