package org.example.exercise14;

import java.util.Scanner;

public class HuvudProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyQueue myQueue = new MyQueue();

        System.out.print("Amount of producers: ");
        Producer[] producersArray = new Producer[sc.nextInt()];
        for (int i = 0; i < producersArray.length ; i++) {
            System.out.println("Producer nr " + (i+1) + ": ");
            System.out.println("Interval Producer: ");
            int time = sc.nextInt();
            System.out.println("Word to place in queue: ");
            String word = sc.next();
            producersArray[i] = new Producer(word, time, myQueue);
        }

        System.out.println("Amount of consumers: ");
        Consumer[] consumersArray = new Consumer[sc.nextInt()];
        for (int i = 0; i < consumersArray.length; i++) {
            System.out.println("Consumer nr "+ (i+1) + ":");
            System.out.println("Interval Consumer: ");
            int time = sc.nextInt();
            consumersArray[i] = new Consumer(time, myQueue);
        }


       // Starta trådarna
        for(Producer producer : producersArray) {
            producer.aktivitet.start();
        }

        for(Consumer consumer : consumersArray) {
            consumer.aktivitet.start();
        }

        try {
            Thread.sleep(10000);
            System.out.println("\nAntal objekt kvar i kön: " + myQueue.listSize());
            myQueue.printQueue();
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
