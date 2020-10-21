import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.*;

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

    public static String encryptTwoKeys (String input, int key1, int key2) {
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
            while (myReader.hasNext()) {
                input += myReader.nextLine();
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
            eyeballDecrypt("pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. ntaa adjs!");
            WordLengths wordLengths = new WordLengths();
            String decrypt = wordLengths.decrypt(input);
            System.out.println(decrypt);
            String decrypt_two = wordLengths.decryptTwoKeys(input);
            System.out.println(decrypt_two);
        }
        catch (Exception exception) {
            throw new FileNotFoundException();
        }
    }

    public static void eyeballDecrypt(String encrypted) {
        CaesarCipher caesarCipher = new CaesarCipher();
        for (int i = 0; i < 26; i++) {
            String decrypted = caesarCipher.encrypt(encrypted, i);
            System.out.println(decrypted);
        }
    }

    public static void main(String[] args) throws Exception {
            testCaesar();
        //testCountWordLengths();
    }

    public static void testCountWordLengths() throws IOException {
        Scanner scanner= new Scanner(new File("Java Programming Arrays, " +
                "Lists, and Structured Data/test.txt"));
        int[] counts = new int[15];
        WordLengths wordLengths = new WordLengths();
        wordLengths.countWordLength(scanner, counts);
    }
}

class WordLengths extends CaesarCipher {
    public void countWordLength(Scanner scanner, int[] counts) throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        while (scanner.hasNext()) {
            String currString = scanner.next();
            ///TODO: change .contains to .matches with regex
            if (currString.contains(",") || currString.contains(".") || currString.contains("!") || currString.contains("?")) {
                currString = currString.substring(0 , currString.length()-1);
            }
                arrayList.add(currString.toLowerCase());
        }
        for (String s : arrayList) {
            if (s.length()>counts.length) {
                counts[counts.length - 1] += 1;
            }else {
                counts[s.length() - 1] += 1;
            }

        }
        for (int i = 0 ; i< counts.length ; i++) {
            System.out.println(counts[i] + " words of length " + (i + 1));
        }
    }

    public String decrypt (String input){
        String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] counts = new int[alphabet.length()];
        StringBuilder decrypted = new StringBuilder(input.toUpperCase());
        decryptCycle(decrypted, alphabet, counts);
        int maxDex = countLetters(counts);
        int dKey = maxDex - 4 ;
        if (maxDex < 4) {
            dKey = 26 - (4 - maxDex);
        }
        return encrypt(input,26-dKey);
    }
    private void decryptCycle(StringBuilder decrypted, String alphabet, int[] counts) {
        for (int i = 0; i < decrypted.length(); i++) {
            char currChar = decrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                counts[idx] += 1;
            }
        }
    }
    private int decrypt (StringBuilder input){
        String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] counts = new int[alphabet.length()];
        StringBuilder decrypted = new StringBuilder(input.toString().toUpperCase());
        decryptCycle(decrypted, alphabet, counts);
        int maxDex = countLetters(counts);
        int dKey = maxDex - 4 ;
        if (maxDex < 4) {
            dKey = 26 - (4 - maxDex);
        }
        System.out.println("dkey " +dKey + " returned " + (26-dKey));
        return 26-dKey;
    }

    public int countLetters(int[]counts) {
        int maxIndex = 0;
        int maxIdx = 0;
        for (int i = 0 ; i< counts.length ; i++) {
            if (counts[i] > maxIdx) {
                maxIdx = counts[i];
                maxIndex=i;
            }
            //char Z = (char)(65+i);
            //System.out.println(Z + " counted " + counts[i]);
        }
        return maxIndex;
    }

    public String decryptTwoKeys (String input) {
        StringBuilder odd,even;
        int key1 = 0,key2 = 0;
        odd = new StringBuilder();
        even = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (i % 2 == 0) {
                odd.append(currChar);
            }
            else {
                even.append(currChar);
            }
        }
        key1 = decrypt(odd);
        key2 = decrypt(even);
        System.out.println(key1+" "+key2);
        return encryptTwoKeys(input, key1, key2);
    }

}
