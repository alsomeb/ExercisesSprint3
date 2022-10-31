package org.example.threadDemo;

public class ThreadApp {
    public static void main(String[] args) throws InterruptedException {
        Printer p1 = new Printer("Hejsan tråd 1", 1000);
        Printer p2 = new Printer("Hejsan tråd 2", 2000);
        Printer p3 = new Printer("Hejsan tråd 3", 3000);

        // alla 3 körs samtidigt, i den takt dem blivit tillsagda
        // håller på i 10 sek, sen får dem en interrupt

        // man kan ej skriva .run() pga då fastnar man och det blir fel, inte korrekt minneshantering
        // när man använder start() skapas de upp en egen stack för varje!
        p1.start();
        p2.start();
        p3.start();

        Thread.sleep(10000);

        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
    }
}
