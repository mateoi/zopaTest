package com.zopa.mateoi.loans;

/**
 * A creditor is a person that lends money at a particular rate.
 */
public class Creditor {
    private final String name;
    private final double credit;
    private final double interest;

    /**
     * Create a creditor that will lend the given amount at the given interest.
     * @param name The creditor's name
     * @param credit The amount available for lending
     * @param interest The interest of the creditor's loans
     */
    public Creditor(String name, double credit, double interest) {
        this.name = name;
        this.credit = credit;
        this.interest = interest;
    }

    public String getName() {
        return name;
    }

    public double getCredit() {
        return credit;
    }

    public double getInterest() {
        return interest;
    }
}
