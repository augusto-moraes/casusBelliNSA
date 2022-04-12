import java.awt.event.*;

public class EcouteurUnites implements ActionListener {
	
	Terrain fen;
	Unite u;
    
  public EcouteurUnites(Terrain t, Unite u){
    this.fen = t;
    this.u = u;
  }
    
  public void actionPerformed(ActionEvent ae){
	  fen.setNextUnit(u);
  }
}

