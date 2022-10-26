package org.example.exercise1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PictureViewer extends JFrame implements ActionListener, Runnable {

    JLabel bodyTitle = new JLabel();
    JButton button = new JButton("Add Images");
    JButton delete = new JButton("Delete All Images");
    JPanel body = new JPanel();
    JPanel footer = new JPanel();
    JPanel header = new JPanel();

    public PictureViewer() {

        // Header
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("Pic Generator");
        title.setFont(new Font("Sans-serif", Font.BOLD, 22));
        title.setPreferredSize(new Dimension(165, 80));
        title.setHorizontalAlignment(JLabel.CENTER);
        header.add(title);


        // Body
        // Placerar enligt Rutmönster, vänster till höger
        // Ange rader och kolumner i constructor
        GridLayout layout = new GridLayout(3, 3);
        layout.setHgap(3);
        layout.setVgap(3);
        body.setLayout(layout);

        // image label i body
        bodyTitle.setText("Klicka på knappen för att lägga till bilder");
        bodyTitle.setFont(new Font("Sans-serif", Font.BOLD, 20));
        bodyTitle.setHorizontalAlignment(JLabel.CENTER);
        body.add(bodyTitle);


        // knappen i footer
        button.setFocusable(false); // Tar bort den fula ramen på texten
        button.setBackground(Color.decode("#7978FF")); // Octal och Hex färger
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border
        button.setFont(new Font("Sans-serif", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(165, 50)); // justera size
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this); // override nedan

        // delete knappen i body
        delete.setFocusable(false); // Tar bort den fula ramen på texten
        delete.setBackground(Color.decode("#7978FF")); // Octal och Hex färger
        delete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border
        delete.setFont(new Font("Sans-serif", Font.BOLD, 20));
        delete.setPreferredSize(new Dimension(180, 50)); // justera size
        delete.setHorizontalAlignment(JButton.CENTER);
        delete.setVisible(false); //
        delete.addActionListener(this);

        // footer
        footer.setPreferredSize(new Dimension(800, 100));
        footer.add(button);
        footer.add(Box.createHorizontalStrut(20));
        footer.add(delete);


        // Lägger in komponenter
        this.add(header, BorderLayout.NORTH);
        this.add(body, BorderLayout.CENTER); // 5 Konstanter, Nord Syd, Öst, Väst
        this.add(footer, BorderLayout.SOUTH);

        // Mouse Listener
        mouseListener();

        //pack(); // sätter size dynamiskt beroende på komponenter
        setSize(800,800);
        setVisible(true); // osynlig by default
        setResizable(false);
        setLocationRelativeTo(null); // rutan poppas upp i mitten av skärmen
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Annars kommer programmet köras i bakgrunden även om vi exit
    }

    private void mouseListener() {

        // DETTA BLIR SOM EN HOOVER EFFECT
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.BLUE);
                button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                button.setBackground(Color.lightGray);
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.decode("#7978FF")); // Octal och Hex färger
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border
                button.setForeground(Color.BLACK);
            }
        });
    }

    public BufferedImage generateLoremPicSum() throws IOException {
        URL url = new URL("https://picsum.photos/300");
        return ImageIO.read(url); // Läser in img från URL
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(isBodyTitleShowing()) {
            body.remove(bodyTitle);
            body.revalidate();
        }

        // eftersom vi bara har 2 Action Listeners,
        // denna är bättre än den nedan, magic strings farligt
        if(e.getSource() == button) {
            loadAllImages();
        } else {
            clear();
        }
/*
        switch (e.getActionCommand()) {
            case "Add Images" -> loadAllImages();
            case "Delete All Images" -> clear();
        }

 */
    }

    public void loadAllImages() {
        // 9 bilder
        for (int i = 1; i <= 9; i++) {
            Thread thread = new Thread(this);
            thread.start(); // <-- kör våran run metod nedan
        }
        button.setVisible(false);
        delete.setVisible(true);
    }

    public void clear() {
        body.removeAll();
        body.add(bodyTitle);
        body.revalidate();
        body.repaint();

        button.setVisible(true);
        delete.setVisible(false);
    }

    public boolean isBodyTitleShowing() {
        return bodyTitle.isShowing();
    }

    public void loadImageFromUrl() {
        try {
            // generateLoremPicSum() -> bufferedImg som är 300x300
            // Vi skapar en ny Image med 298, 298 (eftersom border är 2px)
            // Lägger denna Image i en ImageIcon
            Image scaled = generateLoremPicSum().getScaledInstance(298, 298, Image.SCALE_SMOOTH);
            JLabel bild = new JLabel(new ImageIcon(scaled));

            // Lägger den i en panel med 300px per bild inkl border
            JPanel bildPanel = new JPanel();
            bildPanel.setPreferredSize(new Dimension(300,300));
            bildPanel.setLayout(new BorderLayout());
            bildPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Lägger in komponenterna
            bildPanel.add(bild);
            body.add(bildPanel);
            revalidate();
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error inläsning bild");
        }
    }

    // trådning
    @Override
    public void run() {
        loadImageFromUrl();
    }
}
