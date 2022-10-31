package org.example.threadDemo;

public class PrinterWithRunnable implements Runnable{
    String message;
    int interval;

    public PrinterWithRunnable(String message, int interval) {
        this.message = message;
        this.interval = interval;
    }

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
