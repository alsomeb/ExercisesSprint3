package org.example.morecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    - Små kryssrutor med text

    - Multival OK, dem hänger ej ihop by default

    - ex. JCheckBox chin = new JCheckBox("Chin", true);
      första är texten, andra om det är intryckt eller inte från början

    - Genererar ActionEvent när användaren kryssar i eller kryssar ur rutan

 */
public class JCheckBoxDemo extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JCheckBox red = new JCheckBox("Red", false);
    private JCheckBox blue = new JCheckBox("Blue", false);
    private JCheckBox yellow = new JCheckBox("Yellow", false);
    private JLabel displayArea = new JLabel("Du har inte valt någon färg");

    public JCheckBoxDemo() {
        // Layout
        panel.setLayout(new GridLayout(4, 1));
        panel.add(red);
        panel.add(blue);
        panel.add(yellow);
        panel.add(displayArea);
        red.setBackground(Color.lightGray);
        blue.setBackground(Color.lightGray);
        yellow.setBackground(Color.lightGray);
        this.add(panel);

        // Listeners
        red.addActionListener(this);
        blue.addActionListener(this);
        yellow.addActionListener(this);


        // JFrame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Kommer få actionEvent när vi klickar i och ur men detta är bara för demo
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == red) {
            if(red.isSelected()) {
                red.setBackground(Color.red);
                displayArea.setText("Du valde rött");
            }
            else {
                red.setBackground(Color.lightGray);
                displayArea.setText("Du INTE valde rött");
            }
        }
        else if (e.getSource() == blue) {
            blue.setBackground(Color.blue);
            displayArea.setText("Du valde blått");
        }
        else if (e.getSource() == yellow) {
            yellow.setBackground(Color.yellow);
            displayArea.setText("Du valde gult");
        }
    }
}
