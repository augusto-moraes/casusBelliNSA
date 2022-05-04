import java.awt.event.*;

public class EcouteurUnites implements ActionListener {
	FenetreJeu fj;
	Terrain fen;
	String type;
    
  public EcouteurUnites(FenetreJeu fj,Terrain fen, String type){
	this.fj = fj;
    this.fen = fen;
    this.type = type;
  }
    
  public void actionPerformed(ActionEvent ae){
	Unite u = null;
	Joueur nextJoueur = fj.getNextJoueur();
	switch(this.type) {
		case "Paysan":
			u = new Paysan(nextJoueur);
			break;
		case "Lancier":
			u = new Lancier(nextJoueur);
			break;
		case "Chevalier":
			u = new Chevalier(nextJoueur);
			break;
		case "Paladin":
			u = new Paladin(nextJoueur);
			break;
		case "Tour":
			u = new Tour(nextJoueur);
			break;
		case "Tourlvl2":
			u = new Tourlvl2(nextJoueur);
			break;
		case "Mine":
			u = new Mine(nextJoueur);
			break;
	}

	fen.setTransition(false);
	fen.setNextUnit(u);
	fj.showPrice();
  }
}

