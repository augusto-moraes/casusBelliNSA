import java.awt.event.*;

public class EcouteurLaunch implements ActionListener {
	
	Affichage fen;
	int nbJoueurs;
    
    public EcouteurLaunch(Affichage f, int joueur){
        fen=f;
        nbJoueurs = joueur;
    }
    
    public void actionPerformed(ActionEvent ae){	
        fen.startGame(nbJoueurs); 
    }
}
