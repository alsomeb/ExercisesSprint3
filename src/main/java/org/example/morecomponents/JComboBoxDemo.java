package org.example.morecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 - Drop down med alternativ, klickar
 - Tar en array som inparameter
 - Arrayen innehåller Strängar med options man kan välja i combobox
 - ActionEvent när man klickar på ett val
 - JComboBox.getSelectedItem() ger oss den rad som blev klickad på, dvs strängen som står på raden

 */

public class JComboBoxDemo extends JFrame implements ActionListener {
    private JPanel p = new JPanel();
    private JLabel label = new JLabel();
    private final String[] colors = {"Röd", "Blå", "Gul"};
    JComboBox<String> colorChooser = new JComboBox<>(colors);


    public JComboBoxDemo() {
        /*
        The default data model is an empty list of objects.
        Use addItem to add items.
        By default the first item in the data model becomes selected.
         */
        colorChooser.setSelectedIndex(-1); // No selection by default
        colorChooser.setEditable(true); // Man kan skriva o klicka enter o få de valet
        colorChooser.addActionListener(this);

        p.setLayout(new GridLayout(2, 1));
        p.add(colorChooser);
        p.add(label);

        this.add(p);

        // JFrame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Lite mer komplex, finns ingen naturlig getSource, samma komponent.
    // Såhär kan man reagera på den
    @Override
    public void actionPerformed(ActionEvent e) {
        if (((String)colorChooser.getSelectedItem()).equalsIgnoreCase("röd")) {
            p.setBackground(Color.red);
            label.setText("Du valde rött");
        } else if (((String)colorChooser.getSelectedItem()).equalsIgnoreCase("blå")) {
            p.setBackground(Color.blue);
            label.setText("Du valde blått");
        } else if (((String)colorChooser.getSelectedItem()).equalsIgnoreCase("gul")) {
            p.setBackground(Color.yellow);
            label.setText("Du valde gult");
        }
    }
}
