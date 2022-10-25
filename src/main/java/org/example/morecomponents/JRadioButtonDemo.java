package org.example.morecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
- Skillnad från CheckBox är att bara 1 knapp kan va intryck åt gången
- Ingår i en ButtonGroup, som man får skapa själv (Klass).
- ActionEvent genereras när en använder trycker in en knapp
 */

public class JRadioButtonDemo extends JFrame implements ActionListener {
    private final JPanel panel = new JPanel();
    private final JRadioButton red = new JRadioButton("Röd", true);
    private final JRadioButton blue = new JRadioButton("Blå", false);
    private final JRadioButton yellow = new JRadioButton("Gul", false);
    private final ButtonGroup bg = new ButtonGroup(); // bara 1 kan va vald!

    private final JLabel displayArea = new JLabel("Du valde rött");

    public JRadioButtonDemo() {
        panel.setBackground(Color.red);
        panel.setLayout(new GridLayout(4, 1));
        panel.add(red);
        panel.add(blue);
        panel.add(yellow);
        panel.add(displayArea);
        bg.add(red);
        bg.add(blue);
        bg.add(yellow);

        this.add(panel);

        red.addActionListener(this);
        blue.addActionListener(this);
        yellow.addActionListener(this);

        // JFrame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == red) {
            panel.setBackground(Color.red);
            displayArea.setText("Du valde rött");
        }
        else if (e.getSource() == blue) {
            panel.setBackground(Color.blue);
            displayArea.setText("Du valde blått");
        }
        else if (e.getSource() == yellow) {
            panel.setBackground(Color.yellow);
            displayArea.setText("Du valde gult");
        }
    }
}
