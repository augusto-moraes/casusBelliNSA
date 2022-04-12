import java.awt.*;
import javax.swing.*;


public class Paladin extends Soldat{
	
	final public Image img = new ImageIcon(getClass().getResource("images/unitelv4.png")).getImage();

  public Paladin(int cout, Joueur appartient, int income,int niveau, int range ){
    super(4, appartient, -4, 4, range );

  }
  
   public Image getImage() {
	  return img;
  }

}
