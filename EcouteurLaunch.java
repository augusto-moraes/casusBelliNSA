import java.awt.event.*;

public class EcouteurLaunch implements ActionListener {
	
	Affichage fen;
	int nbJoueurs;
    
    public EcouteurLaunch(Affichage f, int joueur){
        fen=f;
        nbJoueurs = joueur;
    }
    
    public void actionPerformed(ActionEvent ae){
		
        fen.clearScreen(); 
        fen.startGame(nbJoueurs);

        //FenetreJeu jeu = new FenetreJeu(nbJoueurs); //creation de la nouvelle fenetre de jeu
        //fen.setContentPane(jeu);
   
        //fen.repaint();
        //fen.validate();
        
        //seters de GameManager pour instancier les parametres du jeu
        //game.setJeu(jeu);
        //game.setNbJoueurs(nbJoueurs);    
    }
}

