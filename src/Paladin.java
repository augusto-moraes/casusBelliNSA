import java.awt.*;
import javax.swing.*;


public class Paladin extends Soldat{
	
  // init img dans constructor avec try catch !!!
	final public Image img = new ImageIcon(getClass().getResource("Sprites/images/unitelv4.png")).getImage();

  public Paladin(Joueur appartient, int range ){
    super(4, appartient, -4, 4, range );

  }
  
   public Image getImage() {
	  return img;
  }

}
