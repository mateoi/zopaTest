package com.zopa.mateoi.loans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Loan represents an amount borrowed from one or more creditors.
 */
public class Loan {
    /** Creditors lending money for this loan */
    private final Map<Creditor, Double> creditors;
    /** Number of months the loan must be repaid in */
    private final int term;
    /** Effective interest rate of this loan */
    private final double interest;
    /** Amount borrowed from creditors */
    private final double principal;
    /** Monthly payment */
    private final double payment;

    /**
     * Create a loan from the given creditors
     * @param creditors The lenders giving money
     * @param term The number of months the loan should be repaid in
     */
    public Loan(Map<Creditor, Double> creditors, int term) {
        this.creditors = new HashMap<>();
        this.creditors.putAll(creditors);
        this.term = term;
        payment = calculatePayment();
        principal = calculatePrincipal();
        interest = calculateEffectiveInterest();
    }

    /**
     * Calculates the effective interest rate of this loan by making a weighted average of all creditors.
     * @return The effective interest rate of this loan
     */
    private double calculateEffectiveInterest() {
        double sum = creditors.entrySet().stream(). // Stream all the creditors
                map(e->e.getValue()*e.getKey().getInterest()). // Multiply their rates by their amount loaned
                reduce(0., Double::sum); // And add them all up
        return sum / this.principal;
    }

    /**
     * Calculate the principal of this loan
     * @return The loan's principal
     */
    private double calculatePrincipal() {
        return creditors.values().stream().reduce(0., Double::sum);
    }

    /**
     * Calculate the payment due each month by aggregating the money owed to each creditor
     * @return The total payment due each month
     */
    private double calculatePayment() {
        double payment = 0;
        for (Map.Entry<Creditor, Double> creditor : creditors.entrySet()) {
            payment += calculateSinglePayment(this.term, creditor.getValue(), creditor.getKey().getInterest());
        }
        return payment;
    }

    /**
     * Calculate the fixed-rate payment for a loan with the given principal, rate and term.
     * This is using A = (R/(1-(1+R)^-n))*P, where R is the rate, n is the term and P is the principal.
     * @param term The number of payments to make
     * @param principal The money borrowed initially
     * @param rate The interest rate per period
     * @return The amount due each term
     */
    private static double calculateSinglePayment(int term, double principal, double rate) {
        if (rate == 0) { // prevent division by zero
            return principal / term;
        }
        double proportion = rate / (1 - (Math.pow(1 + rate, -term)));
        return proportion * principal;
    }

    public int getTerm() {
        return term;
    }

    public double getInterest() {
        return interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getPayment() {
        return payment;
    }

    public double getTotalPayment() {
        return payment * term;
    }
}
