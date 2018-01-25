package com.zopa.mateoi.io;

import com.zopa.mateoi.loans.Creditor;
import com.zopa.mateoi.loans.Market;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Singleton class that parses CSV files into Markets.
 */
public class CSVParser {
    /** Singleton instance of the class */
    private static final CSVParser instance = new CSVParser();

    //Private constructor to prevent instantiation outside the class
    private CSVParser() {}

    /**
     * Get the singleton instance of this class
     * @return The instance of CSVParser
     */
    public static CSVParser getInstance() {
        return instance;
    }

    /**
     * Parses a CSV file containing Market data and returns a Market object, or null if the file could not be read.
     * Lines that do not conform to the expected format (name, rate, amount) are ignored.
     * @param filename The location of the file to parse
     * @return A Market object containing the creditors in the market
     */
    public Market parseMarket(String filename) {
        try {
            List<Creditor> creditors = Files.lines(Paths.get(filename)). // Stream the file lines
                    skip(1). // Remove the table headers
                    map(this::parseCreditor). // Parse each line
                    filter(Objects::nonNull). // Remove failed parses
                    collect(Collectors.toList()); // And put everything in a list
            return new Market(creditors);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Parse a single line representing a creditor. Returns null if the string does not conform to the (name, rate,
     * amount) format.
     * @param s The string to parse
     * @return A Creditor object with the data given in the string
     */
    private Creditor parseCreditor(String s) {
        String[] columns = s.trim().split(",");
        if (columns.length != 3) {
            return null;
        }
        try {
            String name = columns[0];
            double rate = Double.parseDouble(columns[1]);
            double amount = Double.parseDouble(columns[2]);
            return new Creditor(name, amount, rate);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
