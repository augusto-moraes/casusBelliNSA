import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Affichage extends JFrame{
    
    private GameManager game;

    private JTextArea credits;
    private JButton PressStart;

   
    public Affichage(GameManager g){
        super("Casus Bellinsa");
        
        game = g;
        
       PressStart = mkButton("images/image.jpg");
        
        
        setSize(1600,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        // ====== Instanciation des widgets de la fenetre entree ======
        PressStart = new JButton("Jouer");
		credits = new JTextArea(20, 100);
		credits.setEditable(false);
		credits.setText();
        

        // ====== Organisation structurelle ======
        this.setLayout(new BorderLayout());

        

        // ===== liaison bouttons <-> écouteurs =====
        PressStart.addActionListener(new EcouteurPressStart(this));

        setVisible(true);
    } 
    
    public JButton mkButton(String url){
		JButton b = new JButton(new ImageIcon(url));
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		return b;
	} 
}

