import java.awt.*;
import javax.swing.*;


public class Paysan extends Soldat{
	
  // init img dans constructor avec try catch !!!
	final public Image img = new ImageIcon(getClass().getResource("Sprites/images/unitelv1.png")).getImage();

  public Paysan( Joueur appartient){
    super(1, appartient, -1,1,1);

  }
  public String toString() {
	  return "Paysan";
  }
  
  public Image getImage() {
	  return img;
  }


}
