import java.awt.*;
import javax.swing.*;


public class Tour extends Batiment {
	final public Image img = new ImageIcon(getClass().getResource("images/Tower.png")).getImage();
    
    public Tour(int cout, Joueur appartient){

    super(cout, appartient, 0, "Tour");
    

  }
    public Image getImage() {
	  return img;
  }




}
