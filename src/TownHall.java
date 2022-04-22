import java.awt.*;
import javax.swing.*;

public class TownHall extends Batiment {
  public Image img;

  public TownHall(int cout, Joueur appartient, int income){
    super(cout, appartient, income, "TownHall");

    try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/Castle.png")).getImage();
    } catch (Exception e) {}
  }

  public Image getImage() { return this.img; }
}
