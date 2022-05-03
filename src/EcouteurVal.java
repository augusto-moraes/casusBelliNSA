import java.awt.event.*;

public class EcouteurVal implements ActionListener {
	private FenetreJeu fj;
	
    public EcouteurVal(FenetreJeu fj){
		this.fj = fj;
    }
    
    public void actionPerformed(ActionEvent ae){
		//Demander le prochain juoeur 
		fj.nextPlayer();
		//Met l'unite StandBy dans terrain NextUnit
		fj.changeNextUnitStandBy(fj.nextJoueur);	
		//Reinitialiser le tour au debut de chaque (Pour le moment deplacement des soldat mais aussi thune...)
	}
}
