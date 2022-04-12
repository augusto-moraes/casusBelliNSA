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
    
    private JButton tour;
    private JButton tourForte;
    private JButton ferme;
    private JButton chateau;
    
    
    public FenetreJeu(int nbJoueurs) {
		setLayout(new BorderLayout());
        //super("Casus Bellinsa");
        
        finDeTour = mkButton("images/finDuTour.png");
        annulerAction = mkButton("images/retourArriere.png");
        parametres = mkButton("images/parametres.png");
        
        uniteNiveau1 = mkButton("images/unitelv1.png");
        uniteNiveau2 = mkButton("images/unitelv2.png");
        uniteNiveau3 = mkButton("images/unitelv3.png");
        uniteNiveau4 = mkButton("images/unitelv4.png");
        
        tour = mkButton("images/Tower.png");
        tourForte = mkButton("images/Strong_tower.png");
        ferme = mkButton("images/Farm.png");
        chateau = mkButton("images/Castle.png");
        
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
        
        JPanel panneauHaut = new JPanel();
        
        panneauHaut.setBackground(Color.BLACK);		
		panneauHaut.add(parametres);
		
		add(panneauHaut,BorderLayout.NORTH);
        
        
        JPanel panneauMilieu = new JPanel();
        
        panneauMilieu.setBackground(Color.BLACK);		
        Terrain  p = new Terrain(1500,700,29,0) ;
		p.setPreferredSize(new Dimension(1500, 700));
        p.addMouseListener(p);
        
		init i = new init(p,nbJoueurs);
		i.initial();
        panneauMilieu.add(p);
        //Essaie pour les ecouteurs,a eliminer ou ailleurs car le joueur depende de game manager 
        uniteNiveau1.addActionListener(new EcouteurUnites(p, new Paysan(1,new Joueur(0),1,1,1))); 
		uniteNiveau2.addActionListener(new EcouteurUnites(p, new Lancier(1,new Joueur(0),1,1,1))); 
		uniteNiveau3.addActionListener(new EcouteurUnites(p, new Chevalier(1,new Joueur(0),1,1,1))); 
		uniteNiveau4.addActionListener(new EcouteurUnites(p, new Paladin(1,new Joueur(0),1,1,1)));  
		
		tour.addActionListener(new EcouteurUnites(p, new Tour(1,new Joueur(0)))); 
        tourForte.addActionListener(new EcouteurUnites(p, new Tourlvl2(1,new Joueur(0)))); 
        ferme.addActionListener(new EcouteurUnites(p, new Mine(1,new Joueur(0),15))); 
       // chateau.addActionListener(new EcouteurUnites(p, new TownHall(1,new Joueur(0),15))); 

        
        add(panneauHaut,BorderLayout.NORTH);
        add(panneauBas,BorderLayout.SOUTH);
        add(panneauMilieu,BorderLayout.CENTER);
        
        //        this.setSize(1000,600);
      //  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  this.setVisible(true);
       
        
    }
    
    public JButton mkButton(String url){
		JButton b = new JButton(new ImageIcon(url));
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		return b;
	}

}

