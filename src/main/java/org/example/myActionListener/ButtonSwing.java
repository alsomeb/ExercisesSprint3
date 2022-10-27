package org.example.myActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonSwing extends JFrame {
    private JPanel panel = new JPanel();
    private JButton button = new JButton("Click me");
    private JButton button2 = new JButton("Click me again");

    public ButtonSwing() {
        panel.add(button);
        panel.add(button2);
        this.add(panel);

        button.addActionListener(new MyActionListener(button)); // egen gjord klass som impl Action Listener

        // lambda med Action Listener, funkar pga det är ett interface med 1 metod bara
        button2.addActionListener(listener -> {
            button2.setText("Klickad!!");
        });

        // Samma som ovan fast denna är utan lambda, ANONYM KLASS
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.setForeground(Color.RED);
            }
        });

        // Funkar ej på MouseAdapter pga den är ej ett interface utan en klass, men däremot funkar en anonym klass
        // Mouse Adapter är bra för då behöver vi inte lägga in ALLA metoder, som vi kanske inte ens kommer använda
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button2.setForeground(Color.BLACK);
            }
        });


        // Frame
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
