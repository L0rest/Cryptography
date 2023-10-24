import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;

public class DesGUI extends JFrame implements WindowListener {

    public JPanel panel;
    public JButton chiffrement, dechiffrement, lecture;
    public Des des;

    public DesGUI() {
        super("DES");
        this.setSize(500, 500);
        this.setVisible(true);
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());

        this.des = new Des();

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        this.chiffrement = new JButton("Chiffrement");
        this.dechiffrement = new JButton("Déchiffrement");
        this.lecture = new JButton("Lecture");

        panel.add(chiffrement);
        panel.add(dechiffrement);
        panel.add(lecture);

        this.add(panel, BorderLayout.SOUTH);

        // Action listeners

        chiffrement.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisir un fichier à chiffrer");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                String[] pathSplit = path.split("\\.");
                String newPath = pathSplit[0] + "_chiffre.txt";

                // Read the file
                Scanner scanner = null;

                try {
                    scanner = new Scanner(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    throw new RuntimeException(fileNotFoundException);
                }

                // Check if the file is empty
                if (!scanner.hasNextLine()) {
                    JOptionPane.showMessageDialog(this, "Le fichier est vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StringBuilder text = new StringBuilder(scanner.nextLine());

                while (scanner.hasNextLine()) {
                    text.append("\n").append(scanner.nextLine());
                }

                // Crypt the text
                String crypt = Arrays.toString(des.crypte(text.toString()));

                // Write to file
                try {
                    FileWriter fileWriter = new FileWriter(newPath);
                    fileWriter.write(crypt);
                    fileWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Create popup
                JOptionPane.showMessageDialog(this, "Le fichier a été chiffré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        });


    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void main(String[] args) {
        DesGUI desGUI = new DesGUI();
    }
}
