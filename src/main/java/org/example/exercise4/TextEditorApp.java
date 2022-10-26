package org.example.exercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
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
    private final JButton newFile = new JButton("Skapa ny");
    private final JTextArea textArea = new JTextArea(30, 60);
    private final JScrollPane scrollPane = new JScrollPane(textArea);


    public TextEditorApp() {
        // Load Cache txt file
        String[] options = getComboBoxArray();
        filesDropDown = new JComboBox<>(options);
        filesDropDown.setSelectedIndex(-1);

        // Själva main Panelen
        container.setLayout(new BorderLayout());
        this.add(container);

        // Header
        header.setLayout(new GridLayout(1, 7));
        header.add(filePathLabel);
        //header.add(filePath);
        header.add(filesDropDown);
        header.add(open);
        header.add(save);
        header.add(print);
        header.add(newFile);
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
        newFile.addActionListener(this);

        // Frame
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == print) {
            handlePrintFile();
        } else if (e.getSource() == open) {
            handleOpenFile();
        } else if (e.getSource() == save) {
            handleSaveFile();
        } else if (e.getSource() == newFile) {
            handleNewFile();
        }
    }

    private void createFileByUrl(Path url) throws IOException {
        Files.createFile(url);
    }

    public String loadCacheFile(){
       Path path = Path.of("src/main/resources/cache.txt");
       String line;
       StringBuilder sb = new StringBuilder();

       try(BufferedReader reader = Files.newBufferedReader(path)) {
           while ((line = reader.readLine()) != null) {
               sb.append(line).append("\n");
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
        if(selectedFile != null) {
            return Path.of("src/main/resources/" + selectedFile);
        }
        throw new NullPointerException();
    }

    public String getFileNamePrompt() {
        String rootUrl = "src/main/resources/";
        String fileName = JOptionPane.showInputDialog("Vad skall filen heta? (.txt läggs till automatiskt)");
        if (fileName == null) {
            return "";
        }
        return rootUrl.concat(fileName.trim().concat(".txt"));
    }

    public void writeNewFileToCache(String fileName) {
        Path path = Path.of("src/main/resources/cache.txt");
        int lastSlash = fileName.lastIndexOf("\\") + 1;

        try(BufferedWriter bfWriter = new BufferedWriter(new FileWriter(path.toFile(),true))) {
            bfWriter.write(fileName.substring(lastSlash));
            bfWriter.write("\n");
            System.out.println(fileName.substring(lastSlash));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // formaterar en Path string till bara filnamnet.txt
    public String shrinkPathToFileNameString(Path url) {
        int lastSlash = url.toString().lastIndexOf("\\") + 1;
        return url.toString().substring(lastSlash);
    }

    public void handleNewFile() {
        Path url = Path.of(getFileNamePrompt());
        if(!url.toString().isBlank()) { // klickar man avbryt så får man en tom sträng därför vi har denna lösning
            try {
                createFileByUrl(url); // kommer söka i resources om den kan skapa filnamnet, annars generera fel, KOMMER INTE HELLER SKRIVA till Cache om felet kastas
                writeNewFileToCache(url.toString());
                filesDropDown.addItem(shrinkPathToFileNameString(url));
                revalidate();
                repaint();
            } catch (FileAlreadyExistsException existsException) {
                JOptionPane.showMessageDialog(null, "Filnamnet finns redan, välj annat namn");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Något gick fel med sparandet");
            }
        }
    }

    public void handleSaveFile() {
        try {
            Path url = getSelectedItemFilePath();
            textArea.write(Files.newBufferedWriter(url));
            JOptionPane.showMessageDialog(null, "Sparat till fil!");
        } catch (NullPointerException ne) {
            JOptionPane.showMessageDialog(null, "Du måste välja en fil att spara i");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Kunde ej skriva till fil");
        }
    }

    public void handleOpenFile() {
        try {
            Path url = getSelectedItemFilePath();
            textArea.read(Files.newBufferedReader(url), null);
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Välj en fil att läsa");
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Kunde Ej läsa in från fil");
        }
    }

    public void handlePrintFile() {
        try {
            textArea.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(null, "Kan ej Skriva ut");
            ex.printStackTrace();
        }
    }
}

