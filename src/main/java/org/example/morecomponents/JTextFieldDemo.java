package org.example.morecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTextFieldDemo extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JPanel resultPanel = new JPanel();
    JLabel info = new JLabel("Skriv något");
    JTextField textField = new JTextField(10); //10 är bredden,
    // När man trycker enter genereras ett ActionEvent! <-----
    JLabel resultLabel = new JLabel("Du skrev: ");

    public JTextFieldDemo() {
        setLayout(new GridLayout(2, 1));
        add(panel);
        panel.add(info);
        panel.add(textField);
        textField.addActionListener(this);

        resultPanel.add(resultLabel);
        add(resultPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == textField) {
            resultLabel.setText("Du skrev: " + textField.getText());
        }
    }
}
