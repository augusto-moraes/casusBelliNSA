import java.awt.event.*;

public class EcouteurValidate implements ActionListener {
	private FenetreJeu fj;
	
    public EcouteurValidate(FenetreJeu fj){
		this.fj = fj;
    }
    
    public void actionPerformed(ActionEvent ae){
		//Demander le prochain juoeur 
		fj.nextPlayer();
		//Met l'unite StandBy dans terrain NextUnit
		fj.changeNextUnitStandBy(fj.getNextJoueur());	
		//Reinitialiser le tour au debut de chaque (Pour le moment deplacement des soldat mais aussi thune...)
	}
}
