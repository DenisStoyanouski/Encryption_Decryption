package encryptdecrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<String, String> arguments = new HashMap<>();

    private static int key;

    private static String text;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
           arguments.put(args[i], args[i + 1]);
        }

        String mode = arguments.getOrDefault("-mode", "enc");
        key = Integer.parseInt(arguments.getOrDefault("-key", "0"));
        text = arguments.getOrDefault("-data", "");

        switch(mode) {
            case "enc" :
                System.out.println(encrypt());
            break;
            case "dec" :
                System.out.println(decrypt());
            break;
            default:
                System.out.println("Unknown mode");
                break;
        }

    }

    private static String input() {
        return scanner.nextLine();
    }

    private static String encrypt() {
        StringBuilder encrypt = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
                char letter = (char) (text.charAt(i) + key);
                encrypt.append(letter);
        }
        return encrypt.toString();
    }

    private static String decrypt() {
        StringBuilder decrypt = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char letter = (char) (text.charAt(i) - key);
            decrypt.append(letter);
        }
        return decrypt.toString();
    }
}
