package org.example.threadQueueExercise;

public class Line implements Runnable {
    public Thread aktivitet = new Thread(this);
    private final Customer customer;
    private final long interval;
    private final MyQueue queue;

    public Line(Customer customer, long seconds, MyQueue queue) {
        this.customer = customer;
        this.interval = seconds * 1000;
        this.queue = queue;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(interval);
                queue.put(customer);
                System.out.println(customer + ", joined the queue");
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}
