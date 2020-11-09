import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

class BigInteger implements IBigInteger {
    private List<Integer> number;

    public BigInteger (String number) {
        this.number = convertToList(number);
    }

    public BigInteger (int[] array) {
        List<Integer> number = new ArrayList<Integer>();
        for (int numbs : array) {
            number.add(numbs);
        }
        this.number = number;
    }

    private List<Integer> convertToList(String input) {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            numbers.add(input.charAt(i) - '0');
        }
        numbers = normalize(numbers);
        return numbers;
    }

    private List<Integer> normalize (List<Integer> list) {
        int lengthOfZerosArray = 0;
        for (Integer number : list) {
            if (number.equals(0)) {
                lengthOfZerosArray++;
            } else {
                break;
            }
        }
        list.subList(0,lengthOfZerosArray).clear();
        return list;
    }

    private void swapLists (BigInteger secondNumber) {
        BigInteger temporary = this;
        this.number = secondNumber.number;
        secondNumber.number = temporary.number;
    }

    @Override
    public String toString(){
        this.number = normalize(this.number);
        StringBuilder output = new StringBuilder();
        for (Integer i : this.number) {
            output.append(i);
        }
    return output.toString();
    }

    public BigInteger add(BigInteger secondNumber) {
        BigInteger firstNumber = this;
        //BigInteger secondNumber = secNum;
        int maxLength = Math.max(firstNumber.number.size(), secondNumber.number.size());
        int[] output = new int[maxLength + 1];
        int difference = Math.abs(firstNumber.number.size() - secondNumber.number.size());
        if(firstNumber.number.size() > secondNumber.number.size()) {
            for (int i = 0; i < difference; i++) {
                secondNumber.number.add(i,0);
            }
        } else {
            for (int i = 0; i < difference; i++) {
                firstNumber.number.add(i,0);
            }
        }
        for (int i = 0; i < maxLength ; i++) {
            int sum = (firstNumber.number.get(i) + secondNumber.number.get(i) + output[i + 1]);
            if (sum < 10) {
                    //output.set(i, sum);
                output[i + 1] = sum;
            } else {
                    //output.set(i, sum % 10);
                    //output.set(i - 1, sum / 10);
                output[i + 1] = sum % 10;
                output[i] += sum / 10;
            }
        }
        return new BigInteger(output);
    }

    public BigInteger substract (BigInteger secondNumber) {
        BigInteger firstNumber = this;
        int maxLength = Math.max(firstNumber.number.size(), secondNumber.number.size());
        int[] output = new int[maxLength + 1];
        if (firstNumber.number.size() > secondNumber.number.size()) {

        }

        return secondNumber;
    }




}
