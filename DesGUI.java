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

    public JButton chiffrement, dechiffrement;
    public TripleDes tDes;
    public JPanel p;
    public JLabel l;

    public DesGUI() {
        super("DES");
        this.setSize(300, 200);
        this.addWindowListener(this);
        this.setLayout(new GridLayout(2, 1));

        this.tDes = new TripleDes();

        this.p = new JPanel(new GridLayout(1, 2));
        this.l = new JLabel("Please choose an option");
        this.chiffrement = new JButton("Encryption");
        this.dechiffrement = new JButton("Decryption");

        this.add(this.l);
        this.l.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.p);
        this.p.add(chiffrement);
        this.p.add(dechiffrement);

        this.setVisible(true);


        // Action listeners

        chiffrement.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to encrypt");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                String[] pathSplit = path.split("\\.");
                String newPath = pathSplit[0] + "_encrypt.txt";

                // Read the file
                Scanner scanner = null;

                try {
                    scanner = new Scanner(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    throw new RuntimeException(fileNotFoundException);
                }

                // Check if the file is empty
                if (!scanner.hasNextLine()) {
                    JOptionPane.showMessageDialog(this, "The file is empty !", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StringBuilder text = new StringBuilder(scanner.nextLine());

                while (scanner.hasNextLine()) {
                    text.append("\n").append(scanner.nextLine());
                }

                // Crypt the text
                String crypt = Arrays.toString(tDes.tripleCrypte(text.toString()));

                // Write to file
                try {
                    FileWriter fileWriter = new FileWriter(newPath);
                    fileWriter.write(crypt);
                    fileWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Create popup
                JOptionPane.showMessageDialog(this, "The file has been successfully encrypted !", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        dechiffrement.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file to decrypt");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                String[] pathSplit = path.split("\\.");
                String newPath = pathSplit[0] + "_decrypt.txt";

                // Read the file
                Scanner scanner = null;

                try {
                    scanner = new Scanner(selectedFile);
                } catch (FileNotFoundException fileNotFoundException) {
                    throw new RuntimeException(fileNotFoundException);
                }

                // Check if the file is empty
                if (!scanner.hasNextLine()) {
                    JOptionPane.showMessageDialog(this, "The file is empty !", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StringBuilder text = new StringBuilder(scanner.nextLine());

                while (scanner.hasNextLine()) {
                    text.append("\n").append(scanner.nextLine());
                }

                System.out.println(text.toString());

                // Convert the string to an array of int
                int[] array = Arrays.stream(text.substring(1, text.length() - 1).split(", ")).mapToInt(Integer::parseInt).toArray();

                // Decrypt the text
                String decrypt = tDes.tripleDecrypte(array);

                // Write to file
                try {
                    FileWriter fileWriter = new FileWriter(newPath);
                    fileWriter.write(decrypt);
                    fileWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Create popup
                JOptionPane.showMessageDialog(this, "The file has been successfully decrypted !", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        new DesGUI();
    }
}