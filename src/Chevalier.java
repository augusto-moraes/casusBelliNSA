import java.awt.*;
import javax.swing.*;

public class Chevalier extends Soldat{
	
  // init img dans constructor avec try catch !!!
	final public Image img = new ImageIcon(getClass().getResource("Sprites/images/unitelv3.png")).getImage();
	
  public Chevalier(int cout, Joueur appartient, int income,int niveau, int range ){
    super(3, appartient, -3, 3, range );
  }
  
  public Image getImage() {
	  return img;
  }

}
