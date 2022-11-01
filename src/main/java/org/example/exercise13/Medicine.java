package org.example.exercise13;

public class Medicine implements Runnable{
    private final String medName;
    private final double intervall;

    public Medicine(String medName, double ggrPerMinut) {
        this.medName = medName;
        this.intervall = (60/ggrPerMinut) * 1000; // dvs 6 ggr == var 10s
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(Math.round(intervall)); // Rundar av så det blir enklare
                System.out.println("Dags att ta " + medName);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
