package com.akinyele;


import java.text.NumberFormat;
import java.util.Scanner;


public class Main {
    final static byte MONTHS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        // Mortgage calculation challenge
        int principal = (int) readNumber("Principal: ", 1_000, 1_000_000);

        float apr = (float) readNumber("apr: ", 1, 30);

        byte years = (byte) readNumber("Period (Years): ", 1, 30);


        printMortgage(principal, apr, years);
        printBalanc(principal, apr, years);


    }

    private static void printMortgage(int principal, float apr, byte years) {
        double mortgagePayment = calcMortgage(principal, apr, years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgagePayment);
        System.out.println("Mortgage\n________\nMonthly payments: " + mortgageFormatted);
    }

    private static void printBalanc(int principal, float apr, byte years) {
        System.out.println();
        System.out.println("Payment Schedule\n________________");
        for (short month = 1; month <= years * MONTHS; month++) {
            double balPerMonth = calcBalance(principal, apr, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balPerMonth));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min || value <= max)
                break;
                System.out.println("Enter a number between " + min + " and " + max);


        }
        return value;
    }

    public static double calcMortgage(int principal, float apr, byte years) {


        short numOfPayPeriod = (short)( years * MONTHS);
        float interest = apr / PERCENT / MONTHS;

        double mortgagePayment = principal * ((interest * Math.pow((1 + interest), numOfPayPeriod))
                / (Math.pow((1 + interest), numOfPayPeriod) - 1));

        return mortgagePayment;
    }

    public static double calcBalance(int principal, float apr, byte years, double numOfPaymentMade) {


        short numOfPayPeriod = (short) (years * MONTHS);
        float interest = apr / PERCENT / MONTHS;

        double balance = (principal *
                (Math.pow((1 + interest), numOfPayPeriod) - Math.pow((1 + interest), numOfPaymentMade)))
                / (Math.pow((1 + interest), numOfPayPeriod) - 1);

        return balance;
    }

}
