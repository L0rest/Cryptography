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

    JLabel label;
    JPanel panel1;
    JTextField textField;
    JButton bDecryption, bEncryption, bRead;
    Des des;

    public DesGUI() {
        super("DES");
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        this.label = new JLabel();
        this.textField = new JTextField();
        this.bDecryption = new JButton("Déchiffrer");
        this.bEncryption = new JButton("Chiffrer");
        this.bRead = new JButton("Lire");
        this.panel1 = new JPanel(new GridLayout(1,3));

        this.add(panel1, BorderLayout.SOUTH);
        this.add(label, BorderLayout.NORTH);

        panel1.add(bEncryption);
        panel1.add(bDecryption);
        panel1.add(bRead);

        // Add listener to buttons
        bEncryption.addActionListener(e -> {
            // Select a text file
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);

            try {
                // Encrypt the text and create a new file with the encrypted text
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    Scanner scanner = new Scanner(file);
                    // read the text
                    StringBuilder text = new StringBuilder();

                    while (scanner.hasNextLine()) {
                        text.append(scanner.nextLine()).append("\n");
                    }

                    // encrypt the text
                    String encryptedText = Arrays.toString(this.des.crypte(text.toString()));
                    // create a new file
                    File encryptedFile = new File(file.getParent() + "/encrypted.txt");

                    FileWriter fileWriter = new FileWriter(encryptedFile);

                    fileWriter.write(encryptedText);
                    fileWriter.close();

                    label.setText("Le fichier a été chiffré avec succès");
                }
            } catch (IllegalArgumentException ex) {
                // Create popup
                JOptionPane.showMessageDialog(this, "Le fichier n'est pas un fichier texte", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bDecryption.addActionListener(e -> {
            // Select a text file
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);

            // Check if the file is a text file
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                // read the text
                StringBuilder text = new StringBuilder();

                while (scanner.hasNextLine()) {
                    text.append(scanner.nextLine());
                }

                // Convert the string to an array of int
                int[] textInt = Arrays.stream(text.toString().split(",")).mapToInt(Integer::parseInt).toArray();

                // Decrypt the text
                String decryptedText = this.des.decrypte(textInt);

                // create a new file
                File decryptedFile = new File(file.getParent() + "/decrypted.txt");

                FileWriter fileWriter = null;

                try {
                    fileWriter = new FileWriter(decryptedFile);
                    fileWriter.write(decryptedText);
                    fileWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                label.setText("Le fichier a été déchiffré avec succès");
            }

        });

        bRead.addActionListener(e -> {

        });

        this.des = new Des();

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
        desGUI.setVisible(true);
        desGUI.addWindowListener(desGUI);
    }
}
