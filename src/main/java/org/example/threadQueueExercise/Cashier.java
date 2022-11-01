package org.example.threadQueueExercise;

public class Cashier implements Runnable {
    public Thread aktivitet = new Thread(this);
    private final long interval;
    private final MyQueue queue;

    public Cashier(long seconds, MyQueue queue) {
        this.interval = seconds * 1000;
        this.queue = queue;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(interval);
                Customer currentCustomer = queue.take();
                System.out.println("Cashier serving: " + currentCustomer);
            } catch (InterruptedException e) {
                break; // om avbryts så stäng ner
            }
        }
    }
}
