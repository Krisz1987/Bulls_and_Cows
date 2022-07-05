package bullscows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static final List<Character> fullList = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't','u',
            'v',  'w',  'x', 'y', 'z'));

    static final String stringOfFulList = fullList.stream().map(String::valueOf).collect(Collectors.joining());

    static int codeLength;
    static int numberOfPossible;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        int counter = 1;
        int bulls = 0;
        int cows = 0;
        String lengthOfCode = "";

        checkTheCodeLength(lengthOfCode);

        checkNumberOfPossible();

        String codeString = makeACode();

        writeTheCodeLine();

        startTheGame(bulls, cows, counter, codeString);

    }

    private static void startTheGame(int bulls, int cows, int counter, String codeString) {
        System.out.println("Okay, let's start a game!");
        while (bulls != codeLength) {
            cows = 0;
            bulls = 0;
            System.out.println("Turn " + counter + ":");
            String number = scanner.next();

            for (int i = 0; i < number.length(); i++) {
                if (number.charAt(i) == codeString.charAt(i)) {
                    bulls++;
                }
            }
            for (int i = 0; i < codeString.length(); i++) {
                for (int j = 0; j < number.length(); j++) {
                    if (codeString.charAt(i) == number.charAt(j)) {
                        cows++;
                        break;
                    }
                }
            }

            cows -= bulls;
            if (cows == 0 && bulls == 0) {
                System.out.println("Grade: None.");
            } else if (cows == 0 && bulls > 0) {
                System.out.println("Grade: " + bulls + " bull(s). ");
            } else if (cows > 0 && bulls == 0) {
                System.out.println("Grade: " + cows + " cow(s). ");
            } else {
                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s). ");
            }

            counter++;
        }

        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static void checkNumberOfPossible() {
        System.out.println("Input the number of possible symbols in the code:");
        numberOfPossible = scanner.nextInt();
        if (numberOfPossible > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
        if (codeLength > numberOfPossible) {
            System.out.println("Error: it's not possible to generate a code with a length of " + codeLength  + " with " + numberOfPossible + " unique symbols.");
            System.exit(0);
        }
    }

    private static void checkTheCodeLength(String lengthOfCode) {
        System.out.println("Please, enter the secret code's length:");
        lengthOfCode = scanner.nextLine();
        codeLength = 0;
        try {
            codeLength = Integer.parseInt(lengthOfCode);
            if (codeLength < 1) {
                System.out.println("Error: code must be bigger!");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + lengthOfCode + "\" isn't a valid number.");
            System.exit(0);
        }
    }

    private static void writeTheCodeLine() {
        int endPosition = numberOfPossible - 1;
        System.out.print("The secret is prepared: " + "*".repeat(codeLength));
        if (numberOfPossible < 11) {
            System.out.print(" (0-" + (numberOfPossible - 1) + ")");
        } else {
            System.out.print(" (0-9, a-" + stringOfFulList.charAt(endPosition) + ").");
        }
        System.out.println();
    }

    public static String makeACode() {
        List<Character> codeCharList = fullList.subList(0, numberOfPossible);
        Collections.shuffle(codeCharList);
        String possibleCodeString = codeCharList.stream().map(String::valueOf).collect(Collectors.joining());
        String codeString = possibleCodeString.substring(0, codeLength);
        return codeString;
    }

}
