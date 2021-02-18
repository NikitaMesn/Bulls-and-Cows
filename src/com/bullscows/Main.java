package com.bullscows;

import java.util.*;


public class Main {
    private static int attemptCounter = 0;
    private static String randomCode = "";
    private static int lenCode = 0;
    private static int numSymbols = 0;
    private static boolean endGame = false;
    private static int bulls = 0;
    private static int cows = 0;


    public static void main(String[] args) {
        startGame();
        guessCode();

    }
    private static void startGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        lenCode = sc.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        numSymbols = sc.nextInt();
        if (lenCode > numSymbols || numSymbols > 36 ||  numSymbols <= 0) {
            System.out.print("Error: can't generate a secret number \n");
            lenCode = 0;
            startGame();
        }else if ( lenCode <= 0) {
            lenCode = 0;
            startGame();
        } else {
            generateCode();
            System.out.print("Okay, let's start a game!\n") ;
            System.out.println(randomCode);
        }
    }


    private static void guessCode() {
        Scanner sc = new Scanner(System.in);
        attemptCounter++;

        while (!endGame) {
            System.out.printf("Turn %s: \n", attemptCounter);
            String answer = sc.nextLine();
            checkAnswer(answer);
        }


    }

    private static void BullCowsCounter(String answer) {
        bulls = 0;
        cows = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (randomCode.charAt(i) == answer.charAt(i)) {
                bulls++;
                continue;
            }
            if (randomCode.contains("" + answer.charAt(i))) {
                cows++;
            }
        }
    }

    private static void checkAnswer (String answer) {
        attemptCounter++;
        BullCowsCounter(answer);

        if (cows != 0 && bulls != 0) {
            System.out.printf("Grade: %s bull(s) and %s cow(s).\n",
                    bulls,
                    cows);
        } else if (cows == 0 && bulls != 0) {
            if (bulls == randomCode.length()) {
                System.out.printf("Grade: %s bull(s).\n",
                        bulls);
                System.out.println("Congratulations! You guessed the secret code.");
                endGame = true;
            } else {
                System.out.printf("Grade: %s bull(s).\n",
                        bulls);
            }
        } else if (cows != 0) {
            System.out.printf("Grade: %s cow(s).\n",
                    cows);
        } else {
            System.out.println("Grade: None.");
        }

    }


    private static void generateCode( ) {
        StringBuilder code = new StringBuilder();
        List<Character> chars = new ArrayList<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        String asterisk = multyString(lenCode);
        String postAsterisk = pstAsterisk(numSymbols, chars);

        List<Character> randomList = chars.subList(0, numSymbols);
        Collections.shuffle(randomList);



        for (int i = 0; i < lenCode; i++) {
            code.append(randomList.get(i));
        }
        randomCode = code.toString();

        System.out.print("The secret code is prepared: " + asterisk + postAsterisk + "\n");
    }

    private static String multyString( int x) {
        String str = "";
        for (int i = 0; i < x; i++) {
            str += "*";
        }
        return str + " ";
    }

    private  static String pstAsterisk(int x, List<Character> lst) {
        String str;

        if (x <= 10) {
            str = "(0-" + lst.get(x - 1) + ")";
            //System.out.println(str);
        } else {
            str = "(0-9, a-" + lst.get(x - 1) + ")";
        }
        return  str;

    }
}
