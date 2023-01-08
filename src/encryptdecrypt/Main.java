package encryptdecrypt;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<String, String> arguments = new HashMap<>();

    private static int key;

    private static String text;

    private static String fileNameIn;

    private static String fileNameOut;

    private static String algorithm;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
           arguments.put(args[i], args[i + 1]);
        }

        String mode = arguments.getOrDefault("-mode", "enc");
        key = Integer.parseInt(arguments.getOrDefault("-key", "0"));
        text = arguments.getOrDefault("-data", "");
        fileNameIn = arguments.getOrDefault("-in", "");
        fileNameOut = arguments.getOrDefault("-out", "");
        algorithm = arguments.getOrDefault("-alg", "");


        switch(mode) {
            case "enc" : encrypt();
            break;
            case "dec" : decrypt();
            break;
            default:
                System.out.println("Unknown mode");
                break;
        }

    }

    private static String input() {
        return scanner.nextLine();
    }

    private static void encrypt() {
        StringBuilder encrypt = new StringBuilder();
        if (text.isEmpty()) {
            text = readFile();
        }
        if ("shift".equals(algorithm) || algorithm.isEmpty()) {
            for (int i = 0; i < text.length(); i++) {
                int letter = text.charAt(i);
                if (letter >= 65 && letter <= 90) {
                    letter += key;
                    if (letter > 90) {
                        letter = letter % 90 + 64;
                    }
                } else if(letter >= 97 && letter <= 122) {
                    letter += key;
                    if (letter > 122) {
                        letter = letter % 122 + 96;
                    }
                }
                encrypt.append((char) letter);
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                char letter = (char) (text.charAt(i) + key);
                encrypt.append(letter);
            }
        }

        if (fileNameOut.isEmpty()) {
            System.out.println(encrypt);
        } else {
            writeFile(encrypt.toString());
        }

    }

    private static void decrypt() {
        StringBuilder decrypt = new StringBuilder();
        if (text.isEmpty()) {
            text = readFile();
        }
        if ("shift".equals(algorithm) || algorithm.isEmpty()) {
            for (int i = 0; i < text.length(); i++) {
                int letter = text.charAt(i);
                if (letter >= 65 && letter <= 90) {
                    letter -= key;
                    if (letter < 65) {
                        letter = letter + 26;
                    }
                } else if(letter >= 97 && letter <= 122) {
                    letter -= key;
                    if (letter < 97) {
                        letter = letter + 26;
                    }
                }
                decrypt.append((char) letter);
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                char letter = (char) (text.charAt(i) - key);
                decrypt.append(letter);
            }
        }
        if (fileNameOut.isEmpty()) {
            System.out.println(decrypt);
        } else {
            writeFile(decrypt.toString());
        }
    }

    private static String readFile () {
        StringBuilder data = new StringBuilder();
        File fileInput = new File(fileNameIn);
        try (FileReader fr = new FileReader(fileInput)) {
            int letter = fr.read();
            while (letter != -1) {
               data.append((char) letter);
               letter = fr.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found");;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }

    private static void writeFile(String data) {
        File fileOutput = new File(fileNameOut);
        try(FileWriter fw = new FileWriter(fileOutput)) {
            fw.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
