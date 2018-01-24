package com.zopa.mateoi;

/**
 * Main class. Launches application and validates arguments
 */
public class Main {
    /** The minimum permissible loan */
    private static final double MIN_LOAN = 1000;
    /** The maximum permissible loan */
    private static final double MAX_LOAN = 15000;
    /** The value loans must be multiples of */
    private static final double LOAN_INTERVAL = 100;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: zopa <marketdata> <amount>");
            System.exit(1);
        }
        String filename = args[0];
        double amount = parseAmount(args[1]);
        System.out.println(filename);
        System.out.println(amount);
    }

    /**
     * Parses and validates the given string to see if it's a valid loan amount.
     * Exits with an error code if the value cannot be parsed or if it's an invalid amount.
     * @param s The string to parse and validate
     * @return The amount to loan, if valid.
     */
    private static double parseAmount(String s) {
        double amount = 0;
        try {
            amount = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("Could not parse amount. Please enter a number without currency symbols.");
            System.exit(2);
        }
        if (amount < MIN_LOAN || amount > MAX_LOAN) {
            System.out.format("Please enter an amount between £" + (int)MIN_LOAN + " and £" + (int)MAX_LOAN + ".");
            System.exit(3);
        } else if (amount % LOAN_INTERVAL != 0) {
            System.out.println("Please enter a multiple of £" + (int)LOAN_INTERVAL + " for the amount.");
            System.exit(3);
        }
        return amount;
    }
}
