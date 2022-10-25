package org.example.morecomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Större yta än TextField, där man kan skriva text
// new JTextArea("text", r, k)
// new JTextArea(r, k)
// new JTextArea("text)
// eller utan ngt i constructor

// JScrollPane brukar kombineras med JTextField
// Ger scrollningsfunk till textarea

// JTextArea textArea = new JTextArea(5, 30);
// JScrollPane scrollPane = new JScrollPane(textArea);
// BEHÖVER BARA ADDA scrollPane , för den innehåller textarean!

/*
Metoder TextArea:
t.setText("text") - skriver in
t.getText() - hämtar de som skrivits in
t.getSelectedText() - hämtar markerad text

Skicka in en reader / writer, och de vi läst in hamnar i textrutan!
Alternativt de vi skrivit skrivs med Writern

t.Read(r, null) läser text från readern r och lägger in i arean
t.write(w) skriver texten i arean till writern w, som hamnar i filen
t.print() skriver text till en skrivare (dialogruta öppnas, kommer ej skriva ut något egentligen)
t.append("text") lägger text till befintlig text
t.setLineWrap(bool) anger om långa rader skall brytas

 */
public class JTextAreaDemo extends JFrame implements ActionListener {
    private JPanel bodyPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JTextArea inputArea = new JTextArea(10, 60);
    private JScrollPane scrollPane = new JScrollPane(inputArea);
    private JButton cloneText = new JButton("Clone Written Text");
    private JButton clearText = new JButton("Clear Text");
    private JButton readFile = new JButton("Lorem Ipsum From File");

    public JTextAreaDemo() {
        inputArea.setFont(new Font("Sans-serif", Font.PLAIN, 12));
        inputArea.setLineWrap(true);
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(scrollPane, BorderLayout.CENTER); // innehåller textArea också

        // Får flow layout automatiskt
        buttonPanel.add(cloneText);
        buttonPanel.add(clearText);
        buttonPanel.add(readFile);
        bodyPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(bodyPanel);

        // Listeners
        clearText.addActionListener(this);
        cloneText.addActionListener(this);
        readFile.addActionListener(this);

        // Frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == clearText) {
            inputArea.setText("");
        } else if(e.getSource() == cloneText) {
            inputArea.setText(inputArea.getText() + " " + inputArea.getText());
        } else if (e.getSource() == readFile) {
            try {
                inputArea.read(new BufferedReader(new FileReader("src/main/resources/filen.txt")), null);
            } catch (IOException ex) {
                inputArea.setText("Kunde ej läsa in från fil");
            }
        }
    }
}
