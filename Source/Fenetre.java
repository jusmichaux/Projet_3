import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    private Box bHor;
    private JButton b1;
    private JCheckBox coche1;
    private JCheckBox coche2;
    private JCheckBox coche3;
    private JCheckBox coche4;
    private JTextField txt;
    private JLabel etiq;

    public Fenetre(){
        this.setTitle("Ma première fenêtre Java");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        Container contenu = getContentPane();
        
        bHor = Box.createVerticalBox();
        contenu.add(bHor);
        b1 = new JButton ("Bouton1");
        bHor.add(b1);
        txt = new JTextField(40);
        bHor.add(txt);
        coche1= new JCheckBox("32x32");
        bHor.add(coche1);
        coche2= new JCheckBox("64x64");
        bHor.add(coche2);
        coche3= new JCheckBox("128x128");
        bHor.add(coche3);
        coche4= new JCheckBox("256x256");
        bHor.add(coche4);
        etiq = new JLabel ("Bonjour");
        bHor.add(etiq);
    }
}


