package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String encrypt = "we found a treasure!";
        StringBuilder decrypt = new StringBuilder();
        for (int i = 0; i < encrypt.length(); i++) {
            if (encrypt.charAt(i) >= 'a' && encrypt.charAt(i) <= 'z') {
                decrypt.append((char) (97 - encrypt.charAt(i) + 122));
            } else {
                decrypt.append(encrypt.charAt(i));
            }
        }

        System.out.println(decrypt);
    }
}
