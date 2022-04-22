import java.awt.*;
import javax.swing.*;


public class Lancier extends Soldat{
	
  // init img dans constructor avec try catch !!!
	final public Image img = new ImageIcon(getClass().getResource("Sprites/images/unitelv2.png")).getImage();

  public Lancier(int cout, Joueur appartient, int income,int niveau, int range ){
    super(2, appartient, -2, 2, range );

  }
  
   public Image getImage() {
	  return img;
  }

}
