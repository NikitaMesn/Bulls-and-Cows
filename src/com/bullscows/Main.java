package com.bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String code = "4587";

        Scanner sc = new Scanner(System.in);
        String pNum = sc.nextLine();
        int bulls = 0;
        int cows = 0;
        String ans = "None";

        for (int i = 0; i < 4; i++) {
            if (code.charAt(i) == pNum.charAt(i)) {
                bulls++;
                continue;
            }
            if (code.contains("" + pNum.charAt(i))) {
                cows++;
            }
        }

        if (cows != 0 && bulls != 0) {
            System.out.printf("Grade: %s bull(s) and %s cow(s). The secret code is %s",
                    bulls,
                    cows,
                    code);
        } else if (cows == 0 && bulls != 0) {
            System.out.printf("Grade: %s bull(s). The secret code is %s",
                    bulls,
                    code);
        } else if (cows != 0 && bulls == 0) {
            System.out.printf("Grade: %s cow(s). The secret code is %s",
                    cows,
                    code);
        } else {
            System.out.println("Grade: None. The secret code is " + code + ".");
        }
    }
}
