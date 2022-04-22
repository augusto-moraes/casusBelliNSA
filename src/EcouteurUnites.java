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
	  if (type == "Paysan") {
		  u = new Paysan(fj.nextJoueur,1);
	  } else if (type == "Lancier") {
		  u = new Lancier(3,fj.nextJoueur,-1,1,1);
	  } else if (type == "Chevalier") {
		  u = new Chevalier(6,fj.nextJoueur,-2,1,1);
	  }else if (type == "Paladin") {
		  u = new Paladin(fj.nextJoueur,1);
	  } else if (type == "Tour" ) {
		  u = new Tour(2,fj.nextJoueur);
	  }else if (type == "Tourlvl2" ) {
		  u = new Tourlvl2(5,fj.nextJoueur);
	  }else if (type == "Mine" ) {
		  u = new Mine(7,fj.nextJoueur,15);
	  }
	  
	  fen.setNextUnit(u);
  }
}

