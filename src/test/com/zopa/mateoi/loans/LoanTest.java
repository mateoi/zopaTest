package com.zopa.mateoi.loans;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class LoanTest {
    private Loan empty; // Loan with no creditors
    private Loan simple; // Loan with a single creditor
    private Loan complex; // Loan with more than one creditor

    private double delta = 0.00001;
    private double currencyDelta = 0.01;


    @Before
    public void setUp() throws Exception {
        Creditor c1 = new Creditor("Alan", 1400, 0.1 / 12);
        Creditor c2 = new Creditor("Bob", 1000, 0.07 / 12);
        Creditor c3 = new Creditor("Cathy", 4111, 0.02 / 12);
        HashMap<Creditor, Double> emptyMap = new HashMap<>();
        HashMap<Creditor, Double> simpleMap = new HashMap<>();
        HashMap<Creditor, Double> complexMap = new HashMap<>();

        simpleMap.put(c1, 1000.);
        complexMap.put(c1, 1400.);
        complexMap.put(c2, 950.);
        complexMap.put(c3, 2200.);

        empty = new Loan(emptyMap, 36);
        simple = new Loan(simpleMap, 36);
        complex = new Loan(complexMap, 36);
    }

    @Test
    public void getInterest_empty() throws Exception {
        assertEquals(0., empty.getInterest(), delta);
    }

    @Test
    public void getInterest_simple() throws Exception {
        assertEquals(0.1 / 12., simple.getInterest(), delta);
    }

    @Test
    public void getInterest_complex() throws Exception {
        assertEquals(0.055055 / 12, complex.getInterest(), delta);
    }

    @Test
    public void getPrincipal_empty() throws Exception {
        assertEquals(0., empty.getPrincipal(), delta);
    }

    @Test
    public void getPrincipal_simple() throws Exception {
        assertEquals(1000., simple.getPrincipal(), delta);
    }

    @Test
    public void getPrincipal_complex() throws Exception {
        assertEquals(4550., complex.getPrincipal(), delta);
    }

    @Test
    public void getPayment_empty() throws Exception {
        assertEquals(0., empty.getPayment(), delta);
    }

    @Test
    public void getPayment_simple() throws Exception {
        assertEquals(32.27, simple.getPayment(), currencyDelta);
    }

    @Test
    public void getPayment_complex() throws Exception {
        assertEquals(137.52, complex.getPayment(), currencyDelta);
    }

    @Test
    public void getTotalPayment_empty() throws Exception {
        assertEquals(0., empty.getTotalPayment(), currencyDelta);
    }

    @Test
    public void getTotalPayment_simple() throws Exception {
        assertEquals(1161.62, simple.getTotalPayment(), currencyDelta);
    }

    @Test
    public void getTotalPayment_complex() throws Exception {
        assertEquals(4950.76, complex.getTotalPayment(), currencyDelta);
    }

}