package com.zopa.mateoi.loans;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Market represents the current state of the market by keeping track of available creditors and creating new loans.
 */
public class Market {
    /** The creditors in the market */
    private List<Creditor> creditors;

    /**
     * Create a Market with the given creditors
     * @param creditors The creditors currently lending money.
     */
    public Market(List<Creditor> creditors) {
        this.creditors = creditors.stream(). // Stream creditors
                sorted(Comparator.comparing(Creditor::getInterest)). // Sort them by ascending interest rate
                collect(Collectors.toList()); // And put them in a list
    }

    /**
     * Create a loan for the given amount and term at the lowest possible interest rate
     * @param amount The amount to borrow
     * @param term The number of months to repay the loan
     * @return A loan with the best possible interest rate, or null if the loan cannot be financed
     */
    public Loan createLoan(double amount, int term) {
        Map<Creditor, Double> lenders = new HashMap<>(); // The amount each creditor will lend for this loan
        for (Creditor creditor : creditors) {
            if (amount <= 0) {
                return new Loan(lenders, term);
            }
            if (amount <= creditor.getCredit()) {
                lenders.put(creditor, amount);
                amount = 0;
            } else {
                lenders.put(creditor, creditor.getCredit());
                amount -= creditor.getCredit();
            }
        }
        return null;
    }
}
