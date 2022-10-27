package org.example.exercise5extra;

import javax.swing.*;
import java.awt.*;

public class RockPaperScissorApp extends JFrame {
    private final JPanel panel = new JPanel();
    private final JPanel header = new JPanel();
    private final JPanel footer = new JPanel();
    private final JPanel body = new JPanel();
    private final JLabel headerTitle = new JLabel("Rock Paper Scissors");
    private final Image bild = new ImageIcon("src/main/java/org/example/exercise5extra/rps.png").getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);
    private final JLabel imgLabel = new JLabel(new ImageIcon(bild));
    private final JButton scissor = new JButton("Scissor");
    private final JButton rock = new JButton("Rock");
    private final JButton paper = new JButton("Paper");

    private final JButton pcScissor = new JButton("Scissor");
    private final JButton pcRock = new JButton("Rock");
    private final JButton pcPaper = new JButton("Paper");
    private final JLabel result = new JLabel("Resultat: ");



    public RockPaperScissorApp() {
        // Main panel
        panel.setLayout(new BorderLayout());

        // Header
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(800, 200));
        panel.add(header, BorderLayout.NORTH);

        header.setBackground(Color.LIGHT_GRAY);
        headerTitle.setFont(new Font("Sans-serif", Font.BOLD, 20));
        headerTitle.setHorizontalAlignment(JLabel.CENTER);
        headerTitle.setPreferredSize(new Dimension(100, 100)); // X, Y led på title

        header.add(headerTitle, BorderLayout.NORTH);
        header.add(imgLabel, BorderLayout.CENTER);

        // Body User
        body.setLayout(new GridLayout(2, 3));
        body.add(rock);
        body.add(paper);
        body.add(scissor);
        body.add(pcRock);
        body.add(pcPaper);
        body.add(pcScissor);
        panel.add(body, BorderLayout.CENTER);


        // Footer
        pcPaper.setEnabled(false);
        pcRock.setEnabled(false);
        pcScissor.setEnabled(false);

        panel.add(footer, BorderLayout.SOUTH);
        footer.setLayout(new BorderLayout());
        result.setPreferredSize(new Dimension(100, 100));
        result.setFont(new Font("Sans-serif", Font.BOLD, 18));
        result.setHorizontalAlignment(JLabel.CENTER);
        footer.add(result, BorderLayout.NORTH);
        footer.setPreferredSize(new Dimension(800, 200));
        footer.setBackground(Color.LIGHT_GRAY);

        // TODO LISTENER ROCK PAPER SCISSOR
        // TODO KUNNA GE RESULTAT VEM SOM VAN, SCORE HANTERING


        // Frame
        this.add(panel);
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
