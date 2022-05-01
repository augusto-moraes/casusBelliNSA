import java.awt.*;
import javax.swing.*;


public class Tour extends Batiment {

  // init img dans constructor avec try catch !!!
	final public Image img = new ImageIcon(getClass().getResource("Sprites/images/Tower.png")).getImage();
    
  public Tour(int cout, Joueur appartient){
    super(cout, appartient,3, 0, "Tour");
  }

  public Image getImage() {
	  return img;
  }
}
