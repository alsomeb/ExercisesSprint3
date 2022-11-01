package org.example.exercise13;

import javax.swing.*;
import java.util.Scanner;

public class MedicineApp {
    public static void main(String[] args) {
        Scanner sc;
        double ggr = 0;
        boolean go = true;

        while (go) {

            // om inget skrivs in i rutan avsluta programmet
            String medName = JOptionPane.showInputDialog("Vad heter medicinen ?");
            if (medName == null || medName.isBlank()) {
                System.exit(0);
            }

            // om inget skrivs in i rutan avsluta programmet
            String amountString = JOptionPane.showInputDialog("Hur många ggr per minut?");
            if (amountString == null || amountString.isBlank()) {
                System.exit(0);
            }

            sc = new Scanner(amountString);
            if(sc.hasNextDouble()) {
                ggr = sc.nextDouble();
            } else {
                go = false;
            }

            Medicine med = new Medicine(medName, ggr);
            Thread th = new Thread(med);
            th.start();
        }
        System.exit(0);
    }
}
