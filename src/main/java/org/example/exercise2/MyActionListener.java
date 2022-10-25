package org.example.exercise2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {

    JButton btn;

    public MyActionListener(JButton btn) {
        this.btn = btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        btn.setText("Klickad!");
    }
}
