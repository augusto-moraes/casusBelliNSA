import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;

public class FenetreJeu extends JPanel{
	
	private Clip audioClipBoucle;
	private JButton finDeTour;
    private JButton annulerAction;
    private JButton parametres;
    
    private JButton uniteNiveau1;
    private JButton uniteNiveau2;
    private JButton uniteNiveau3;
    private JButton uniteNiveau4;
    
    private JPanel panneauHaut;
    private JPanel ArgentJoueur;
    private JLabel Money;
    private JLabel Income;
    
    private JButton tour;
    private JButton tourForte;
    private JButton ferme;
    private JButton chateau;
    
    private JLabel[] tabMoney;
    private JLabel[] tabRevenus;
    
    private GameManager manager;
    private Terrain ter;

    public Joueur nextJoueur;
    
    public FenetreJeu(GameManager gm) {
        this.manager = gm;
        this.ter = manager.getTerrain();

		setLayout(new BorderLayout());
        
        finDeTour = mkButton("Sprites/images/finDuTour.png");
        
        uniteNiveau1 = mkButton("Sprites/images/unitelv1.png");
        uniteNiveau2 = mkButton("Sprites/images/unitelv2.png");
        uniteNiveau3 = mkButton("Sprites/images/unitelv3.png");
        uniteNiveau4 = mkButton("Sprites/images/unitelv4.png");
        
        tour = mkButton("Sprites/images/Tower.png");
        tourForte = mkButton("Sprites/images/Strong_tower.png");
        ferme = mkButton("Sprites/images/Farm.png");
        chateau = mkButton("Sprites/images/Castle.png");
        
        JPanel panneauBas = new JPanel();
        panneauBas.setBackground(Color.BLACK);
        
        panneauBas.add(ferme);
        panneauBas.add(tour);
        panneauBas.add(tourForte);
        panneauBas.add(uniteNiveau1);
        panneauBas.add(uniteNiveau2);
        panneauBas.add(uniteNiveau3);
        panneauBas.add(uniteNiveau4);
        panneauBas.add(finDeTour);
        
        this.panneauHaut = new JPanel();
        panneauHaut.setBackground(Color.BLACK);	
        
        this.ArgentJoueur = new JPanel(new GridLayout(2,1));
        ArgentJoueur.setBackground(Color.BLACK);	
        
        this.Money = new JLabel();
        Money.setForeground(Color.WHITE);
        Money.setText("    Argent    ");
        ArgentJoueur.add(Money);
        
        this.Income = new JLabel();
        Income.setForeground(Color.WHITE);
        Income.setText("    Income    ");
        ArgentJoueur.add(Income);        

		panneauHaut.add(ArgentJoueur);
		      
        JPanel panneauMilieu = new JPanel();
        panneauMilieu.setBackground(Color.BLACK);		

        panneauMilieu.add(ter);
        //Essaie pour les ecouteurs,a eliminer ou ailleurs car le joueur depende de game manager 
		finDeTour.addActionListener(new EcouteurVal(this));
        uniteNiveau1.addActionListener(new EcouteurUnites(this,ter,"Paysan")); 
		uniteNiveau2.addActionListener(new EcouteurUnites(this,ter,"Lancier")); 
		uniteNiveau3.addActionListener(new EcouteurUnites(this,ter,"Chevalier")); 
		uniteNiveau4.addActionListener(new EcouteurUnites(this,ter,"Paladin"));  
		
		tour.addActionListener(new EcouteurUnites(this,ter,"Tour")); 
        tourForte.addActionListener(new EcouteurUnites(this,ter,"Tourlvl2")); 
        ferme.addActionListener(new EcouteurUnites(this,ter,"Mine")); 
        
     
        add(panneauHaut,BorderLayout.NORTH);
        add(panneauBas,BorderLayout.SOUTH);
        add(panneauMilieu,BorderLayout.CENTER);
 
    }

    public void changeBackground() {
		int newColor = nextJoueur.getColor();
		Color color = new Color(newColor);
		this.panneauHaut.setBackground(color);	
		this.ArgentJoueur.setBackground(color);	
	}	
    
    public JButton mkButton(String url){
		JButton b = new JButton(new ImageIcon(url));
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		return b;
	}
	//Change la variable dans terrain du joueur et met un StandBy
	public void changeNextUnitStandBy(Joueur j) {
		this.ter.setNextUnit(new Unite(0,j,0,0));
	}
	//Demande le prochain joueur 
	public void nextPlayer() {
		manager.nextPlayer();
	}

    public void setNextJoueur(Joueur j) {
        this.nextJoueur = j;
        changeNextUnitStandBy(j);
        changeBackground();
    }

	public void updateBalance() {
		Income.setText("    Income : " + nextJoueur.getIncome()+ "    ");
		Money.setText("   Argent : " + nextJoueur.getMoney() + "    ");
	}
	
	public void showPrice() {
		Income.setText("    Income : " + nextJoueur.getIncome()+ " (" + ter.getNextUnit().getIncome()+")" );
		Money.setText("   Argent : " + nextJoueur.getMoney() + " (" + ter.getNextUnit().getCout() + ")");
	}
}

