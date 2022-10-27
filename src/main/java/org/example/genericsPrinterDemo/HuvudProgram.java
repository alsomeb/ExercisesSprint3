package org.example.genericsPrinterDemo;

public class HuvudProgram {
    public static void main(String[] args) {
        Printer<Integer> intPrinter = new Printer<>(5);
        Printer<String> stringPrinter = new Printer<>("5 hej");
        System.out.println(intPrinter);
        System.out.println(stringPrinter);
    }
}
