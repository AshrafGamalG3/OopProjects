package algorithms;

import java.util.HashMap;

public class CaesarCipher {

private  String text;
private int key;


    public CaesarCipher(String text, int key) {
        this.text = text;
        this.key = key;
        System.out.println(  encryption());
        System.out.println( decryption());
    }
   static {
       System.out.println("This The Caesar Cipher ");
    }


    private String encryptionWithHash() {
        HashMap<Character, Integer> map = generateCharacterIndexMap();

        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            boolean upperCaseChar = Character.isUpperCase(currentChar);
            char lowerCaseChar = Character.toLowerCase(currentChar);

            if (map.containsKey(lowerCaseChar)) {
                int value = map.get(lowerCaseChar);
                int eValue = (value + key) % 26;
                char newChar = (char) ('a' + eValue);
                encrypted.append(upperCaseChar ? Character.toUpperCase(newChar) : newChar);
            } else {
                encrypted.append(currentChar);
            }
        }

        String originalText = this.text;
        this.text = encrypted.toString();
        return "The encryption of " + originalText + " is: " + encrypted;
    }

    private String decryptionWithHash() {
        HashMap<Character, Integer> map = generateCharacterIndexMap();
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            boolean isUpperCase = Character.isUpperCase(currentChar);
            char lowerCaseChar = Character.toLowerCase(currentChar);

            if (Character.isLetter(currentChar) && map.containsKey(lowerCaseChar)) {
                int value = map.get(lowerCaseChar);
                int dValue = (value - key + 26) % 26;
                char newChar = (char) ('a' + dValue);
                decrypted.append(isUpperCase ? Character.toUpperCase(newChar) : newChar);
            } else {
                decrypted.append(currentChar);
            }
        }
        return "The decryption of " + text + " is: " + decrypted;
    }

    private String encryption() {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char newChar = (char) ((currentChar - base + key) % 26 + base);
                encrypted.append(newChar);
            } else {
                encrypted.append(currentChar);
            }
        }

        String originalText = this.text;
        this.text = encrypted.toString();
        return "The encryption of " + originalText + " is: " + encrypted;
    }

    private String decryption() {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char newChar = (char) ((currentChar - base - key + 26) % 26 + base);
                decrypted.append(newChar);
            } else {
                decrypted.append(currentChar);
            }
        }
        String originalText = this.text;
        this.text = decrypted.toString();
        return "The decryption of " + originalText + " is: " + decrypted;
    }


    private  HashMap<Character, Integer> generateCharacterIndexMap() {
        HashMap<Character,Integer> map =new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + i), i);
            map.put((char) ('A' + i), i);
        }

        return map;
    }




}
