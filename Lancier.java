import java.awt.*;
import javax.swing.*;


public class Lancier extends Soldat{
	
	final public Image img = new ImageIcon(getClass().getResource("images/unitelv2.png")).getImage();

  public Lancier(int cout, Joueur appartient, int income,int niveau, int range ){
    super(2, appartient, -2, 2, range );

  }
  
   public Image getImage() {
	  return img;
  }

}
