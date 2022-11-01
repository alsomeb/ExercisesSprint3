package org.example.exercise14;

public class Producer implements Runnable{
    public Thread aktivitet = new Thread(this);
    private String text;
    private long interval;
    private MyQueue q;

    public Producer(String text, long sec, MyQueue q) {
        this.text = text;
        this.interval = sec * 1000; // pga sleep tar ms
        this.q = q;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(interval);
                q.put(text);
                System.out.println("Producer has put " + text + ", in queue");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
