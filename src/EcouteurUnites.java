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
	switch(this.type) {
		case "Paysan":
			u = new Paysan(fj.nextJoueur);
			break;
		case "Lancier":
			u = new Lancier(fj.nextJoueur);
			break;
		case "Chevalier":
			u = new Chevalier(fj.nextJoueur);
			break;
		case "Paladin":
			u = new Paladin(fj.nextJoueur);
			break;
		case "Tour":
			u = new Tour(fj.nextJoueur);
			break;
		case "Tourlvl2":
			u = new Tourlvl2(fj.nextJoueur);
			break;
		case "Mine":
			u = new Mine(fj.nextJoueur);
			break;
	}

	fen.transition = false;
	fen.setNextUnit(u);
	fj.showPrice();
  }
}

