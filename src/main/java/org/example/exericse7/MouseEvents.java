package org.example.exericse7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEvents extends JFrame {
    JPanel panel = new JPanel();
    JButton button = new JButton("Knapp");

    public MouseEvents() {
        button.setFocusable(false);
        button.setBackground(Color.GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.add(button);
        this.add(panel);
        buttonListeners();

        // Frame
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void buttonListeners() {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.red);
                button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.GRAY);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        });
    }

}
