package org.example.threadQueueExercise;

import java.util.ArrayList;
import java.util.List;

public class MyQueue {
    private final List<Customer> customers = new ArrayList<>();

    public int remainderInQueue() {
        return customers.size();
    }

    public synchronized void put(Customer newCustomerInLine) {
        customers.add(newCustomerInLine);
        notify(); // Wakes up a single thread that is waiting on this object's monitor
    }

    public synchronized Customer take() {
        while (customers.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        Customer firstCustomerInLine = customers.get(0);
        // vi har plockat ut den redan ovan så tar vi bort hen i kön
        // Eftersom vi currently hjälper denna kund
        customers.remove(0);
        return firstCustomerInLine;
    }

    public void printRemainingQueue() {
        customers.forEach(System.out::println); // printa alla som är kvar i listan
    }
}
