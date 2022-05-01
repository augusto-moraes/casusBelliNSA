import java.awt.*;
import javax.swing.*;

public class Tourlvl2 extends Batiment {
	
  // init img dans constructor avec try catch !!!
  final public Image img = new ImageIcon(getClass().getResource("Sprites/images/Strong_tower.png")).getImage();
	
  public Tourlvl2(int cout, Joueur appartient){		
		super(cout, appartient,4, 0, "Tour");
  }
  
  public Image getImage() {
	  return img;
  }
}
