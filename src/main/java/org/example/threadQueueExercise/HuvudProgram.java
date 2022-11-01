package org.example.threadQueueExercise;
import java.util.Scanner;

public class HuvudProgram {
    public static void main(String[] args) {

        MyQueue queue = new MyQueue();
        Scanner sc = new Scanner(System.in);

        System.out.print("Amount of Customers: ");
        Line[] lineArray = new Line[sc.nextInt()];
        for (int i = 0; i < lineArray.length ; i++) {
            System.out.println("Customer nr " + (i+1) + ": ");
            System.out.println("Interval customer: ");
            int time = sc.nextInt();
            System.out.println("First Name of Customer: ");
            String name = sc.next();
            lineArray[i] = new Line(new Customer(name, 10), time, queue);
        }


        System.out.println("Amount of Cashiers: ");
        Cashier[] cashiersArray = new Cashier[sc.nextInt()];
        for (int i = 0; i < cashiersArray.length; i++) {
            System.out.println("Cashier nr "+ (i+1) + ":");
            System.out.println("Interval Cashier: ");
            int time = sc.nextInt();
            cashiersArray[i] = new Cashier(time, queue);
        }

        // Starta trådarna
        for(Line line : lineArray) {
            line.aktivitet.start();
        }

        for(Cashier cashier : cashiersArray) {
            cashier.aktivitet.start();
        }

        // Kör såhär länge sen avsluta
        try {
            Thread.sleep(5000);
            System.out.println("\nAntal Kunder kvar i kön: " + queue.remainderInQueue());
            queue.printRemainingQueue();
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
