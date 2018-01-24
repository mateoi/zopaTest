package com.zopa.mateoi.loans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Loan represents an amount borrowed from one or more creditors.
 */
public class Loan {
    /** Creditors lending money for this loan */
    private Map<Creditor, Double> creditors;
    /** Number of months the loan must be repaid in */
    private int term;
    /** Effective interest rate of this loan */
    private double interest;
    /** Amount borrowed from creditors */
    private double principal;
    /** Monthly payment */
    private double payment;

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
        interest = calculateEffectiveInterest(payment, principal);
    }

    private double calculateEffectiveInterest(double payment, double principal) {
        //TODO: this method
        return 0;
    }

    private double calculatePrincipal() {
        //TODO: this method
        return 0;
    }

    private double calculatePayment() {
        //TODO: this method
        return 0;
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
