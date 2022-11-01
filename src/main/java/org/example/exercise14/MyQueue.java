package org.example.exercise14;

import java.util.ArrayList;
import java.util.List;


public class MyQueue{
    private List<String> listan = new ArrayList<>();

    // Antal element i kön
    public int listSize() {
        return listan.size();
    }

    public synchronized void put(String queueObject) {
        listan.add(queueObject);
        notify(); // Wakes up a single thread that is waiting on this object's monitor.
    }

    public synchronized String take() {
        while (listan.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        String objInQueue = listan.get(0); // hämtar obj i kö från listan plats 0
        listan.remove(0); // Tar bort de så faller dem andra obj i kön på rätt plats
        return objInQueue;
    }

    public void printQueue() {
        listan.forEach(queueObj -> System.out.println(queueObj));
    }
}
