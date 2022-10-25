package org.example.swingappdemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// pack() behöver ej användas pga vi har inga komponenter,
// kommer resize dynamiskt beroende på size komponenter

// Behöver ej ärva kan Direkt bara skapa upp ett objekt av typen JFrame också!
// JFrame är själva "Bottenplattan" ramen till GUI

public class SwingDemo extends JFrame implements ActionListener{

    JPanel panel = new JPanel();
    JLabel label = new JLabel("Klicka knappen");
    JButton button = new JButton("Ändra Färg");
    JButton button2 = new JButton("Test");
    JButton button3 = new JButton("Test2");


    public SwingDemo() {
        // panel layout
        //panel.setLayout(new GridLayout(2, 2));
        panel.setLayout(new BorderLayout());

        // Några av Knappens inbyggda metoder
        button.setFocusable(false); // Tar bort den fula ramen på texten
        button.setBackground(Color.decode("#7978FF")); // Octal och Hex färger
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border
        button.setFont(new Font("Sans-serif", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(120, 40));
        button2.addActionListener(this); // lyssnare

        // Några av Labels inbyggda metoder
        label.setFont(new Font("Sans-serif", Font.BOLD, 20));
        label.setForeground(Color.decode("#4649FF"));

        // lägger in panel i frame sedan komponenter i Frame
        this.add(panel);
        panel.add(label, BorderLayout.NORTH);
        panel.add(button, BorderLayout.EAST);
        panel.add(button2, BorderLayout.WEST);
        panel.add(button3, BorderLayout.SOUTH);

        //setSize(400, 400);
        pack(); // sätter size dynamiskt beroende på komponenter
        setVisible(true); // osynlig by default
        setLocationRelativeTo(null); // rutan poppas upp i mitten av skärmen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Annars kommer programmet köras i bakrunden

        // En simpel ActionListener på knappen för att ändra färg på den
        buttonListener();
    }

    // Kanske snyggare såhär än att impl interfacet ?
    private void buttonListener() {
        button.addActionListener(actionEvent -> { // LAMBDA METOD
            button.setPreferredSize(new Dimension(200, 40));
            button.setBorder(BorderFactory.createLineBorder(Color.decode("#7978FF"), 2));
            button.setBackground(Color.decode("#B2B2B2"));
            button.setForeground(Color.decode("#7978FF"));
            button.setText("Hejsan din jävel!");
            pack();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(button2.getText().equalsIgnoreCase("Test")) {
            label.setText("Hej");
        } else {
            label.setText("Hopp");
        }
    }
}
