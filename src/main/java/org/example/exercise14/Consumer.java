package org.example.exercise14;

public class Consumer implements Runnable {
    public Thread aktivitet = new Thread(this);
    private long interval;
    private MyQueue q;

    public Consumer(long sec, MyQueue q) {
        interval = sec * 1000;
        this.q = q;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(interval);
                String objInQueue = q.take(); // tar obj på plats 0 i kön
                System.out.println("Consumer took: " + objInQueue);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
