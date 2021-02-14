package com.bullscows;

import java.util.*;


public class Main {
    public static int attemptCounter = 0;
    public static String randomCode = "";
    public static int numDigCod = 0;
    public static boolean endGame = false;
    public static int bulls = 0;
    public static int cows = 0;


    public static void main(String[] args) {
        startGame();
        guessCode();

    }
    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        numDigCod = sc.nextInt();
        if (numDigCod > 10) {
            System.out.printf("Error: can't generate a secret number " +
                            "with a length of %s because there aren't enough unique digits. \n",
                    numDigCod);
            numDigCod = 0;
            startGame();
        }else if ( numDigCod <= 0) {
            numDigCod = 0;
            startGame();
        } else {
            generateCode(numDigCod);
            System.out.print("Okay, let's start a game!\n") ;
            //System.out.println(randomCode);
        }
    }


    public static void guessCode() {
        Scanner sc = new Scanner(System.in);
        attemptCounter++;

        while (!endGame) {
            System.out.printf("Turn %s: \n", attemptCounter);
            String answer = sc.nextLine();
            checkAnswer(answer);
        }


    }

    public static void BullCowsCounter(String answer) {
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

    public static void checkAnswer (String answer) {
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
        } else if (cows != 0 && bulls == 0) {
            System.out.printf("Grade: %s cow(s).\n",
                    cows);
        } else {
            System.out.println("Grade: None.");
        }

    }


    public static void generateCode(int num) {
        StringBuilder code = new StringBuilder("");
        List<Integer> randomList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(randomList);

        for (int i = 0; i < num; i++) {
            if (i == 0 && randomList.get(i) == 0) {
                Collections.shuffle(randomList);
                i--;
            }
            code.append(randomList.get(i));
        }
        randomCode = code.toString();
    }

}