package org.example.testSwing;

import javax.swing.*;
import java.awt.*;

public class SwingApp extends JFrame {
    JPanel panel = new JPanel();
    JButton button = new JButton("TEst");
    JButton button2 = new JButton("TEst2");
    JButton button3 = new JButton("TEst3");

    public SwingApp() {
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        button.setBounds(0, 0, 150, 150);
        button2.setBounds(150, 0, 150, 150);
        button3.setBounds(300, 0, 150, 150);
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        System.out.println(button.getX());
        System.out.println(button2.getX());
        System.out.println(button3.getX());


        // JFrame
        this.add(panel);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
