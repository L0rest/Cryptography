import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DesGUI extends JFrame implements WindowListener {

    JLabel label;
    JPanel panel1, panel2;
    JTextField textField;
    JButton bDecryption, bEncryption;
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
        this.panel1 = new JPanel(new GridLayout(2, 1));
        this.panel2 = new JPanel(new GridLayout(1, 2));

        this.add(panel1, BorderLayout.SOUTH);
        panel1.add(textField);
        panel1.add(panel2);

        panel2.add(bEncryption);
        panel2.add(bDecryption);

        this.add(label, BorderLayout.NORTH);

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
