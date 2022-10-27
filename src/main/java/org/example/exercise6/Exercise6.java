package org.example.exercise6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise6 extends JFrame {
    private final JPanel panel = new JPanel();
    private final JButton button1 = new JButton("Knapp1");
    private final JButton button2 = new JButton("Knapp2");
    private final JTextField textField = new JTextField("Första", 10);

    public Exercise6 () {
        panel.add(button1);
        panel.add(button2);
        panel.add(textField);
        this.add(panel);


        listeners();

        // Frame
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void listeners() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                panel.requestFocusInWindow();
            }
        });

        panel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBackground(Color.GRAY);
                textField.setForeground(Color.WHITE);
                textField.setFont(new Font("Sans-serif", Font.BOLD, 18));
            }
        });

        panel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                textField.setText("Du tappade focus");
            }
        });
    }
}
