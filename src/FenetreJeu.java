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
    public JPanel panneauMoneyIncome;
    
    private JButton tour;
    private JButton tourForte;
    private JButton ferme;
    private JButton chateau;
    
    private JLabel[] tabMoney;
    private JLabel[] tabRevenus;
    
    private GameManager manager;
    private Terrain p;

    public Joueur nextJoueur;
    
    public FenetreJeu(GameManager gm) {
        this.manager = gm;
        this.p = manager.getTerrain();

		setLayout(new BorderLayout());
        
        finDeTour = mkButton("Sprites/images/finDuTour.png");
        annulerAction = mkButton("Sprites/images/retourArriere.png");
        parametres = mkButton("Sprites/images/parametres.png");
        
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
        
        panneauBas.add(annulerAction);
        panneauBas.add(ferme);
        panneauBas.add(tour);
        panneauBas.add(tourForte);
        panneauBas.add(uniteNiveau1);
        panneauBas.add(uniteNiveau2);
        panneauBas.add(uniteNiveau3);
        panneauBas.add(uniteNiveau4);
        panneauBas.add(finDeTour);
        
        this.panneauHaut = new JPanel();
        this.panneauMoneyIncome = new JPanel();
        
        panneauHaut.setBackground(Color.BLACK);	
		panneauHaut.add(parametres);
		panneauHaut.add(panneauMoneyIncome);
		
  
		
		add(panneauHaut,BorderLayout.NORTH);
        
        JPanel panneauMilieu = new JPanel();
        
        panneauMilieu.setBackground(Color.BLACK);		

        // Set terrain stuff (sur game manager?)
		p.setPreferredSize(new Dimension(1500, 700));
        p.addMouseListener(p);
        // à mettre sur game manager si possible et enlever game manager des parametres de fenetre jeu?
		manager.generateTerrain();

        panneauMilieu.add(p);
        //Essaie pour les ecouteurs,a eliminer ou ailleurs car le joueur depende de game manager 
		finDeTour.addActionListener(new EcouteurVal(this));
        uniteNiveau1.addActionListener(new EcouteurUnites(this,p,"Paysan")); 
		uniteNiveau2.addActionListener(new EcouteurUnites(this,p,"Lancier")); 
		uniteNiveau3.addActionListener(new EcouteurUnites(this,p,"Chevalier")); 
		uniteNiveau4.addActionListener(new EcouteurUnites(this,p,"Paladin"));  
		
		tour.addActionListener(new EcouteurUnites(this,p,"Tour")); 
        tourForte.addActionListener(new EcouteurUnites(this,p,"Tourlvl2")); 
        ferme.addActionListener(new EcouteurUnites(this,p,"Mine")); 
        
        this.tabMoney = new JLabel[manager.playersList.size()];
		for(int i = 0; i < tabMoney.length; i++) {
			tabMoney[i] = new JLabel();
			tabMoney[i].setText("Argent joueur "+(i+1)+": "+manager.getMoneyJoueurs()[i]);
			panneauMoneyIncome.add(tabMoney[i]);
		}
		
		this.tabRevenus = new JLabel[manager.playersList.size()];
		for(int i = 0; i < tabRevenus.length; i++) {
			tabRevenus[i] = new JLabel();
			tabRevenus[i].setText("Revenus joueur "+(i+1)+": "+manager.getIncomeJoueurs()[i]);
			panneauMoneyIncome.add(tabRevenus[i]);
		}
       
       

        
        add(panneauHaut,BorderLayout.NORTH);
        add(panneauBas,BorderLayout.SOUTH);
        add(panneauMilieu,BorderLayout.CENTER);
        
 
       
        
    }
    public void changeBackground() {
		int c = nextJoueur.getColor();
		Color colo = new Color(c);
		this.panneauHaut.setBackground(colo);	
		this.panneauMoneyIncome.setBackground(colo);	
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
		this.p.setNextUnit(new Unite(0,j,0,0));
	}
	//Demande le prochain joueur 
	public void NextPlayer() {
		manager.nextPlayer();
	}

    public void setNextJoueur(Joueur j) {
        this.nextJoueur = j;
        changeBackground();
    }
    
    public void afficheMoney() {
		for(int i = 0; i < manager.playersList.size(); i++) {
			tabMoney[i].setText("Argent joueur "+(i+1)+": "+manager.getMoneyJoueurs()[i]);
		}
	}
	
	public void afficheRevenus() {
		for(int i = 0; i < manager.playersList.size(); i++) {
			tabRevenus[i].setText("Revenus joueur "+(i+1)+": "+manager.getIncomeJoueurs()[i]);
		}
	}
}

