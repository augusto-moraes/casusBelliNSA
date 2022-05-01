import java.awt.*;
import javax.swing.*;


public class Chevalier extends Soldat{
	
  // init img dans constructor avec try catch !!!
	final public Image img = new ImageIcon(getClass().getResource("Sprites/images/unitelv3.png")).getImage();
	
  public Chevalier(Joueur appartient ){
    super(3, appartient, -3, 3, 1 );
  }
  public String toString() {
	  return "Chevalier";
  }
  
  public Image getImage() {
	  return img;
  }

}
