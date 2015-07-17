import java.awt.Dimension;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JLabel;
public class JPasswordField2 extends JFrame implements ActionListener {
  
  JPasswordField passwordField1 = null; 
  public JPasswordField2 (String titre){
    super(titre);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    init();
    }
  
  public static void main(String args[]) {
    JPasswordField2 app = new JPasswordField2("Afficher image");
    app.setVisible(true);
  }
  
  public void init() {
    JFrame f = new JFrame("ma fenetre");
    f.setSize(300, 100);
    JPanel pannel = new JPanel();

    passwordField1 = new JPasswordField("");
    passwordField1.setPreferredSize(new Dimension(100, 20));
    pannel.add(passwordField1);
    
    JLabel label = new JLabel(new ImageIcon("programme.gif") );
    this.add(label, BorderLayout.CENTER);
    this.pack(); 
    JButton bouton1 = new JButton("Afficher");
    bouton1.addActionListener(this);
    
    pannel.add(bouton1);
    f.getContentPane().add(pannel);
    f.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    
    System.out.println("texte saisie = " + String.copyValueOf(passwordField1.getPassword()));
  }

}