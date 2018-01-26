package com.zopa.mateoi.loans;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarketTest {
    private Market emptyMarket; // Market with no creditors
    private Market simpleMarket; // Market with only one creditor
    private Market complexMarket; // Market with many creditors

    private double currencyDelta = 0.01;
    private double delta = 0.00001;

    @Before
    public void setUp() throws Exception {
        Creditor c1 = new Creditor("Alan", 1400, 0.1 / 12);
        Creditor c2 = new Creditor("Bob", 1000, 0.07 / 12);
        Creditor c3 = new Creditor("Cathy", 4111, 0.02 / 12);

        List<Creditor> emptyList = new ArrayList<>();
        List<Creditor> simpleList = new ArrayList<>();
        List<Creditor> complexList = new ArrayList<>();

        simpleList.add(c1);
        complexList.add(c1);
        complexList.add(c2);
        complexList.add(c3);

        emptyMarket = new Market(emptyList);
        simpleMarket = new Market(simpleList);
        complexMarket = new Market(complexList);
    }

    @Test
    public void createLoan_emptyMarket_zeroLoan() throws Exception {
        Loan loan = emptyMarket.createLoan(0., 36);
        assertEquals(0., loan.getInterest(), delta);
        assertEquals(0., loan.getPayment(), currencyDelta);
        assertEquals(0., loan.getPrincipal(), currencyDelta);
    }

    @Test
    public void createLoan_emptyMarket_nonZeroLoan() throws Exception {
        assertNull(emptyMarket.createLoan(1000., 36));
    }

    @Test
    public void createLoan_simpleMarket_zeroLoan() throws Exception {
        Loan loan = simpleMarket.createLoan(0., 36);
        assertEquals(0., loan.getInterest(), delta);
        assertEquals(0., loan.getPayment(), currencyDelta);
        assertEquals(0., loan.getPrincipal(), currencyDelta);
    }

    @Test
    public void createLoan_simpleMarket_affordableLoan() throws Exception {
        Loan loan = simpleMarket.createLoan(1000., 36);
        assertEquals(0.1 / 12, loan.getInterest(), delta);
        assertEquals(32.27, loan.getPayment(), currencyDelta);
        assertEquals(1000., loan.getPrincipal(), currencyDelta);
    }

    @Test
    public void createLoan_simpleMarket_unAffordableLoan() throws Exception {
        assertNull(simpleMarket.createLoan(4000., 36));
    }

    @Test
    public void createLoan_complexMarket_zeroLoan() throws Exception {
        Loan loan = complexMarket.createLoan(0., 36);
        assertEquals(0., loan.getInterest(), delta);
        assertEquals(0., loan.getPayment(), currencyDelta);
        assertEquals(0., loan.getPrincipal(), currencyDelta);
    }

    @Test
    public void createLoan_complexMarket_affordableLoan() throws Exception {
        Loan loan = complexMarket.createLoan(4200., 36);
        assertEquals(0.0211 / 12, loan.getInterest(), delta);
        assertEquals(120.50, loan.getPayment(), currencyDelta);
        assertEquals(4200., loan.getPrincipal(), currencyDelta);
    }

    @Test
    public void createLoan_complexMarket_unAffordableLoan() throws Exception {
        assertNull(complexMarket.createLoan(10000., 36));
    }

}