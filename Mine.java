import java.awt.*;
import javax.swing.*;


public class Mine extends Batiment {
	final public Image img = new ImageIcon(getClass().getResource("images/Farm.png")).getImage();
    
    public Mine(int cout, Joueur appartient, int income) {
		super(cout, appartient, income, "Mine");
    

  }
  
   public Image getImage() {
	  return img;
  }




}
