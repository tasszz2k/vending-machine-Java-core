package service;

import java.util.Scanner;

/**
 * @author tasszz2k
 * @version 1.0
 * @date 09/06/2021 18:44
 */
public class InputValidator {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Get integer number input from keyboard
     *
     * @param min
     * @param max
     * @return integer number
     */
    public static int getIntValue(int min, int max) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(sc.next());
                if (value <= max && value >= min) {
                    return value;
                } else {
                    System.out.printf("Value input out of range (%d - %d)!\n", min, max);
                }
            } catch (Exception e) {
                System.out.printf("Input must be integer!\n");
            }
            System.out.print("Enter again: ");
        }
    }

    /**
     *  Confirm Y/N ?
     *
     * @return true/false
     */
    public static boolean getBoolean() {
        String value = sc.next();
        return value.equalsIgnoreCase("Y");
    }
}
