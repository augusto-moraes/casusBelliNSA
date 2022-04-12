import java.awt.*;
import javax.swing.*;

public class TownHall extends Batiment {
	final public Image img = new ImageIcon(getClass().getResource("images/Castle.png")).getImage();
    
    public TownHall(int cout, Joueur appartient, int income){

    super(cout, appartient, income, "TownHall");
    

  }
        public Image getImage() {
	  return img;
  }




}
