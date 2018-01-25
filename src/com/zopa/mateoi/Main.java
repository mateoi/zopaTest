package com.zopa.mateoi;

import com.zopa.mateoi.io.CSVParser;
import com.zopa.mateoi.loans.Loan;
import com.zopa.mateoi.loans.Market;

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
    /** The default loan term, in months */
    private static final int DEFAULT_LOAN_TERM = 36;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: zopa <marketdata> <amount>");
            System.exit(1);
        }
        String filename = args[0];
        double amount = parseAmount(args[1]);
        Market market = CSVParser.getInstance().parseMarket(filename);
        if (market == null) {
            System.out.println("Could not open " + filename);
            System.exit(5);
        }
        Loan loan = market.createLoan(amount, DEFAULT_LOAN_TERM);
        if (loan == null) {
            System.out.println("It is not possible to finance this loan at this time");
            System.exit(4);
        }
        printLoanTerms(loan);
    }

    /**
     * Outputs the terms of the requested loan
     * @param loan The loan to print
     */
    private static void printLoanTerms(Loan loan) {
        System.out.println("Requested amount: £" + (int) loan.getPrincipal());
        System.out.format("Rate: %.1f%%%n", 100 * 12 * loan.getInterest());
        System.out.format("Monthly repayment: £%.2f%n", loan.getPayment());
        System.out.format("Total repayment: £%.2f%n", loan.getTotalPayment());
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
