import java.awt.*;
import javax.swing.*;

public class EcranVictoire extends JFrame{
    private JPanel principal;

    private JLabel label;
    private JLabel vicStr;

    private Joueur winner;
  
    public EcranVictoire(Joueur winner, int largeur, int hauteur){
        super("Fin de jeu");

        this.winner = winner;

        setSize(largeur,hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal = new JPanel(new GridLayout(2,1));
        principal.setBackground(new Color(this.winner.getColor()));

        vicStr = new JLabel("Victoire !!");
        vicStr.setForeground(Color.WHITE);

        vicStr.setHorizontalAlignment(SwingConstants.CENTER); 
        vicStr.setVerticalAlignment(SwingConstants.CENTER); 

        label = new JLabel("Le joueur " + this.winner.getId() + " a gagne !!");
        label.setForeground(Color.WHITE);

        label.setHorizontalAlignment(SwingConstants.CENTER); 
        label.setVerticalAlignment(SwingConstants.CENTER); 

        principal.add(vicStr);
        principal.add(label);

        this.add(principal);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
