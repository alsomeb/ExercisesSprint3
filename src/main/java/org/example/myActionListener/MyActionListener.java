package org.example.myActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// DEMO PÅ EGEN ACTION LISTENER
public class MyActionListener implements ActionListener {

    JButton button;

    public MyActionListener(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.BLACK);
        button.setText("Klickad!");
    }
}
