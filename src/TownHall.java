import java.awt.*;
import javax.swing.*;

public class TownHall extends Batiment {

  public TownHall(int cout, Joueur appartient, int income){
    super(cout, appartient,1 , 2, "TownHall");
    try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/Castle.png")).getImage();
    } catch (Exception e) {}
  }
}
