package org.example.threadDemo;

public class Konto {
    private double saldo;

    // simpelt ta ut eller ta in, minus eller plus
    // "synchronized" betyder BARA 1 TRÅD FÅR ÅTKOMST ÅT GÅNGEN, objektet låses som metoden tillhör
    // PRIO spelar ingen roll, Java Köar upp 1 i taget
    public synchronized void transaction(double belopp) {
        saldo += belopp;
    }
}
