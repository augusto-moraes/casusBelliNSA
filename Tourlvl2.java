import java.awt.*;
import javax.swing.*;

public class Tourlvl2 extends Batiment {
		final public Image img = new ImageIcon(getClass().getResource("images/Strong_tower.png")).getImage();
	
    
    public Tourlvl2(int cout, Joueur appartient){		
		super(cout, appartient, 0, "Tour");
  }
  
      public Image getImage() {
	  return img;
  }




}
