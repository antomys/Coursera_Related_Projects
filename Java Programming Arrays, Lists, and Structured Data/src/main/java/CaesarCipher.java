
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class CaesarCipher {

    public static String encrypt (String input, int key){
        String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder encrypted = new StringBuilder(input.toUpperCase());
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0 , key);
        for (int i = 0; i<encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString().toLowerCase();
    }

    public static boolean isVowel (char ch){
        return "AEIOUaeiou".indexOf(ch) != -1;
    }

    public static String replaceVowels (String phrase, char ch) {
        char currChar;
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i< phrase.length(); i++) {
            currChar = result.charAt(i);
            if (isVowel(currChar)) {
                result.setCharAt(result.indexOf(String.valueOf(currChar)),ch);
            }
        }
        return result.toString();
    }

    public static String emphasize (String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for( int i = 0; i < phrase.length(); i++) {
            char currChar = result.charAt(i);
            if ((currChar == Character.toUpperCase(ch) || currChar == ch) && i % 2 == 0) {
                result.setCharAt(i,'*');
            }
            else if (currChar == ch || currChar == Character.toUpperCase(ch)) {
                result.setCharAt(i,'+');
            }
        }
        return result.toString();
    }

    private static String encryptTwoKeys (String input, int key1, int key2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (i % 2 == 0) {
                result.append(encrypt(Character.toString(currChar), key1));
            }
            else {
                result.append(encrypt(Character.toString(currChar), key2));
            }
        }
        return result.toString();
    }

    public static void testCaesar() throws FileNotFoundException {
        int key = 15;
        String input = " ", decrypted;
        try {
            Scanner myReader = new Scanner(new File("Java Programming Arrays, Lists, and Structured Data/test.txt"));
            while (myReader.hasNextLine()) {
                input = myReader.nextLine();
            }
            decrypted = encrypt (input, key);
            myReader.close();
            System.out.println(decrypted);
            boolean bool = isVowel('a');
            System.out.println(bool);
            bool = isVowel('F');
            System.out.println(bool);
            String replaced = replaceVowels("Hello World!", '*');
            System.out.println(replaced);
            String emp = emphasize("Mary Bella Abracadabra", 'a');
            System.out.println(emp);
            String twokeys = encryptTwoKeys(input,8, 21);
            System.out.println(twokeys);
        }
        catch (Exception exception) {
            throw new FileNotFoundException();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        testCaesar();
    }
}
