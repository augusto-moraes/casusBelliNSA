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
			u = new Paysan(fj.getNextJoueur());
			break;
		case "Lancier":
			u = new Lancier(fj.getNextJoueur());
			break;
		case "Chevalier":
			u = new Chevalier(fj.getNextJoueur());
			break;
		case "Paladin":
			u = new Paladin(fj.getNextJoueur());
			break;
		case "Tour":
			u = new Tour(fj.getNextJoueur());
			break;
		case "Tourlvl2":
			u = new Tourlvl2(fj.getNextJoueur());
			break;
		case "Mine":
			u = new Mine(fj.getNextJoueur());
			break;
	}

	fen.setTransition(false);
	fen.setNextUnit(u);
	fj.showPrice();
  }
}

