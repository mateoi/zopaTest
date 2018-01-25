package com.zopa.mateoi.loans;

import java.util.ArrayList;
import java.util.List;

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
        this.creditors = new ArrayList<>();
        this.creditors.addAll(creditors);
    }


}
