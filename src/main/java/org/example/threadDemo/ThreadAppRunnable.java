package org.example.threadDemo;

public class ThreadAppRunnable {
    public static void main(String[] args) {
        PrinterWithRunnable p1 = new PrinterWithRunnable("Hej", 1000);
        PrinterWithRunnable p2 = new PrinterWithRunnable("På", 2000);
        PrinterWithRunnable p3 = new PrinterWithRunnable("Dig", 3000);

        // Klasserna som skall köra egna processer får impl runnable
        // Sedan får man skapa upp new Thread och lägga in klassobjekten i trådobjekten
        // Då körs deras "run()" metod vid ".start()"

        // Skapa trådarna o för in printers
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);

        // Starta upp
        t1.start();
        t2.start();
        t3.start();

        // sov 10 sek sedan avsluta trådarna,
        // dvs låt trådarna köra i 10 sek sedan interrupt() dem!
        try {
            Thread.sleep(10000);
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
