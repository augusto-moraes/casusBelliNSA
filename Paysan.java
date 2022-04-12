import java.awt.*;
import javax.swing.*;


public class Paysan extends Soldat{
	
	final public Image img = new ImageIcon(getClass().getResource("images/unitelv1.png")).getImage();

  public Paysan(int cout, Joueur appartient, int income,int niveau, int range ){
    super(1, appartient, -1,1,1);

  }
  public String toString() {
	  return "Paysan";
  }
  
  public Image getImage() {
	  return img;
  }


}
