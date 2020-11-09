import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        String firstNumber = input.substring(0, input.indexOf('+'));
        String secondNumber = input.substring(input.indexOf('+') + 1);

        ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();

        BigInteger bigInteger = new BigInteger(firstNumber);
        BigInteger bigInteger1 = new BigInteger(secondNumber);
        BigInteger out = bigInteger.add(bigInteger1);
        System.out.println(out.toString());
    }
}
