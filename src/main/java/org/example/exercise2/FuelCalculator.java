package org.example.exercise2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuelCalculator extends JFrame implements ActionListener {
    private final JPanel mainPanel = new JPanel();
    private final JPanel topPanel = new JPanel();
    private final JPanel inputPanel = new JPanel();
    private final JLabel startKm = new JLabel("Ange mätarställning för ett år sedan:");
    private final JLabel endKm = new JLabel("Ange mätarställning nu:");
    private final JLabel fuelUsed = new JLabel("Ange antal liter förbrukad drivmedel:");
    private final JTextField startKmInput = new JTextField();
    private final JTextField endKmInput = new JTextField();
    private final JTextField fuelUsedInput = new JTextField();
    private final JRadioButton bensin = new JRadioButton("Bensin", false);
    private final JRadioButton diesel = new JRadioButton("Diesel", false);
    private final JPanel resultPanel = new JPanel();
    private final JLabel resultKm = new JLabel();
    private final JLabel resultFuel = new JLabel();
    private final JLabel gasMileage = new JLabel();

    private final JButton btn = new JButton("Test");

    public FuelCalculator() {
        // Main Panel
        mainPanel.setLayout(new BorderLayout());

        // Top panel
        // Creating a set of buttons with the same ButtonGroup object means that turning "on"
        // one of those buttons turns off all other buttons in the group.
        topPanel.setLayout(new GridLayout(1, 2));

        bensin.addActionListener(this);
        bensin.setHorizontalAlignment(JRadioButton.CENTER);

        diesel.addActionListener(this);
        diesel.setHorizontalAlignment(JRadioButton.CENTER);

        topPanel.add(bensin);
        topPanel.add(diesel);
        ButtonGroup bg = new ButtonGroup();
        bg.add(bensin);
        bg.add(diesel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Input Panel
        GridLayout grid = new GridLayout(3, 2);
        grid.setHgap(20);
        inputPanel.setLayout(grid);
        inputPanel.setPreferredSize(new Dimension(800, 200));
        inputPanel.add(startKm);
        inputPanel.add(startKmInput);
        inputPanel.add(endKm);
        inputPanel.add(endKmInput);
        inputPanel.add(fuelUsed);
        inputPanel.add(fuelUsedInput);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Result Panel
        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        resultPanel.add(resultKm);
        resultPanel.add(Box.createHorizontalStrut(40));
        resultPanel.add(resultFuel);
        resultPanel.add(Box.createHorizontalStrut(40));
        resultPanel.add(gasMileage);
        resultPanel.add(Box.createHorizontalStrut(40));
        btn.addActionListener(new MyActionListener(btn)); // egen klass för EventHantering
        resultPanel.add(btn);
        resultPanel.setVisible(false);


        // Frame
        this.add(mainPanel); // <-- Innehåller alla andra paneler
        pack();
        setVisible(true); // osynlig by default
        setResizable(false);
        setLocationRelativeTo(null); // rutan poppas upp i mitten av skärmen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Annars kommer programmet köras i bakgrunden även om vi exit
    }

    public void createBensinResult() {
        int totalKm = calcKm();
        double totalBensinInLitres = calcBensin();
        double efficiency = calcBensinEfficiency(totalBensinInLitres, totalKm);
        resultKm.setText("Antal mil körda: " + totalKm);
        resultFuel.setText("Antal liter bensin: " + totalBensinInLitres);
        gasMileage.setText(String.format("Föbrukning per mil: %.2f", efficiency));
        resultPanel.setVisible(true);
    }

    public void createDieselResult() {
        int totalKm = calcKm();
        double totalBensinInLitres = calcBensin();
        double efficiency = calcBensinEfficiency(totalBensinInLitres, totalKm) + 2; // inget logiskt :)
        resultKm.setText("Antal mil körda: " + totalKm);
        resultFuel.setText("Antal liter diesel: " + totalBensinInLitres);
        gasMileage.setText(String.format("Föbrukning per mil: %.2f", efficiency));
        resultPanel.setVisible(true);
    }

    private double calcBensinEfficiency(double resultBensin, int resultKm) {
        return resultBensin / resultKm;
    }

    private double calcBensin() {
        return Double.parseDouble(fuelUsedInput.getText());
    }

    private int calcKm() {
        int startKm = Integer.parseInt(startKmInput.getText());
        int endKm = Integer.parseInt(endKmInput.getText());
        return endKm - startKm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(startKmInput.getText().isBlank() || endKmInput.getText().isBlank() || fuelUsedInput.getText().isBlank())) {
            if (e.getSource() == bensin) {
                createBensinResult();
            } else if (e.getSource() == diesel) {
                createDieselResult();
            }
        }
    }
}

