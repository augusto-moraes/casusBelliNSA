import java.awt.event.*;

public class EcouteurUnites implements ActionListener {
	
	Terrain fen;
	Unite u;
    
  public EcouteurUnites(Terrain fen, Unite u){
    this.fen = fen;
    this.u = u;
  }
    
  public void actionPerformed(ActionEvent ae){
	  fen.setNextUnit(u);
  }
}

