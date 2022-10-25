package org.example.exercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextEditorApp extends JFrame implements ActionListener {
    private final JPanel container = new JPanel();
    private final JPanel header = new JPanel();
    private final JPanel body = new JPanel();

    private final JLabel filePathLabel = new JLabel("Filnamn:");
    // private final JTextField filePath = new JTextField(10);

    private final JComboBox<String> filesDropDown;
    private final JButton open = new JButton("Öppna");
    private final JButton save = new JButton("Spara");
    private final JButton print = new JButton("Skriv ut");
    private final JButton exit = new JButton("Avsluta");

    private final JTextArea textArea = new JTextArea(30, 60);
    private final JScrollPane scrollPane = new JScrollPane(textArea);

    // TODO LÄGG TILL SKAPA NY TEXTFIL (OptionPane = Namn) OCH SPARA TILL DEN FILEN i Cache.txt
    // Kalla på getComboBoxArray() igen i den metod för att uppd listan live i programmet!


    public TextEditorApp() {
        // Load Cache txt file
        String[] options = getComboBoxArray();
        filesDropDown = new JComboBox<>(options);
        filesDropDown.setSelectedIndex(-1);

        // Själva main Panelen
        container.setLayout(new BorderLayout());
        this.add(container);

        // Header
        header.setLayout(new GridLayout(1, 6));
        header.add(filePathLabel);
        //header.add(filePath);
        header.add(filesDropDown);
        header.add(open);
        header.add(save);
        header.add(print);
        header.add(exit);
        container.add(header, BorderLayout.NORTH);

        // Body
        textArea.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        body.add(scrollPane);
        container.add(body, BorderLayout.CENTER);

        // Listeners
        exit.addActionListener(this);
        open.addActionListener(this);
        print.addActionListener(this);
        filesDropDown.addActionListener(this);
        save.addActionListener(this);

        // Frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == print) {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Kan ej Skriva ut");
                ex.printStackTrace();
            }
        } else if (e.getSource() == open) {
            Path url = getSelectedItemFilePath();
            try {
                textArea.read(Files.newBufferedReader(url), null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Kunde Ej läsa in från fil");
            }
        } else if (e.getSource() == save) {
            Path url = getSelectedItemFilePath();
            try {
                textArea.write(Files.newBufferedWriter(url));
                JOptionPane.showMessageDialog(null, "Sparat till fil!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Kunde ej skriva till fil");
            }
        }
    }

    public String loadCacheFile(){
       Path path = Path.of("src/main/resources/cache.txt");
       String line;
       StringBuilder sb = new StringBuilder();

       try(BufferedReader reader = Files.newBufferedReader(path)) {
           while ((line = reader.readLine()) != null) {
               sb.append(line);
           }
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Read from Cache File failed");
       }
        return sb.toString();
    }

    public String[] getComboBoxArray() {
        return loadCacheFile().split("\n");
    }

    // Används för att hämta Path dynamiskt i ActionPerformed
    public Path getSelectedItemFilePath() {
        String selectedFile = (String) filesDropDown.getSelectedItem();
        return Path.of("src/main/resources/" + selectedFile);
    }
}
