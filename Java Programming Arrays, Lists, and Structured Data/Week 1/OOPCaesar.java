import java.io.File;
import java.util.Scanner;

public class OOPCaesar {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public OOPCaesar (int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0 , key);
        this.mainKey = key;
    }

    public String encrypt (String input) {
        StringBuilder encrypted = new StringBuilder(input.toUpperCase());
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString().toLowerCase();
    }

    public String decrypt (String input) {
        OOPCaesar oopCaesar = new OOPCaesar(26 - mainKey);

        return oopCaesar.encrypt(input);
    }

    public String breakCaesarCipher(String input) {
        int [] counts = new int[alphabet.length()];
        StringBuilder decrypted = new StringBuilder(input.toUpperCase());
        decryptCycle(decrypted, alphabet, counts);
        int maxDex = countLetters(counts);
        int dKey = maxDex - 4 ;
        if (maxDex < 4) {
            dKey = 26 - (4 - maxDex);
        }
        OOPCaesar oopCaesar = new OOPCaesar(26 - dKey);
        return oopCaesar.encrypt(input);
    }

    public int breakCaesarCipher(StringBuilder input){
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
        int maxDex = 0;
        //int maxIdx = 0;
        for (int i = 0 ; i< counts.length ; i++) {
            if (counts[i] > counts[maxDex]) {
                maxDex = i;
                //maxIndex=i;
            }
            //char Z = (char)(65+i);
            //System.out.println(Z + " counted " + counts[i]);
        }
        return maxDex;
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

    public static void testCaesar() throws Exception {
        OOPCaesar oopCaesar = new OOPCaesar(15);
        TestCaesarCipher testCaesarCipher = new TestCaesarCipher(12, 2);
        String input = " ";
        Scanner myReader = new Scanner(new File("Java Programming Arrays, Lists, and Structured Data/test.txt"));
        while (myReader.hasNext()) {
            input += myReader.nextLine();
        }
        System.out.println(input);
        System.out.println(testCaesarCipher.decryptTwoKeys(input));
        //System.out.println(oopCaesar.encrypt(input));
    }

    public static void main(String[] args) throws Exception {
        testCaesar();
    }
}

class TestCaesarCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int key1;
    private static int key2;
    private final String shiftedAlphabet1;
    private final String shiftedAlphabet2;

    public TestCaesarCipher(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0 , key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0 , key2);
    }

    public String encryptTwoKeys (String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (i % 2 == 0) {
                result.append(new OOPCaesar(key2).encrypt(Character.toString(currChar)));
            }
            else {
                result.append(new OOPCaesar(key1).encrypt(Character.toString(currChar)));
            }
        }
        return result.toString();
    }

    public String decryptTwoKeys (String input) {
        StringBuilder odd, even;
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
        this.key2 = new OOPCaesar(key1).breakCaesarCipher(odd);
        this.key1 = new OOPCaesar(key2).breakCaesarCipher(even);
        System.out.println(key2+" "+key1);
        return encryptTwoKeys(input);
                //encryptTwoKeys(input, key1, key2);
    }
}
