package org.example.genericsPrinterDemo;

public class Printer<T> {
    T printer;

    public Printer(T printer) {
        this.printer = printer;
    }

    public T getPrinter() {
        return printer;
    }

    public void setPrinter(T printer) {
        this.printer = printer;
    }

    @Override
    public String toString() {
        return "Printer{" +
                "printer=" + printer +
                '}';
    }
}
