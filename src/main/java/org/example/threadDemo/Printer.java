package org.example.threadDemo;

public class Printer extends Thread {
    String message;
    int interval;

    public Printer(String message, int interval) {
        this.message = message;
        this.interval = interval;
    }

    // run finns hos klassen Thread men vi overridar den här
    @Override
    public void run() {
        // körs så länge tråden inte är interrupted (boolean)
        // Annars kan man ha en vanlig metod som börjar o slutar
        // Detta nedan är bara för det är i en while loop
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(interval);
                System.out.println(message);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
