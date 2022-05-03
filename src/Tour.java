import java.awt.*;
import javax.swing.*;

public class Tour extends Batiment {
	
  public Tour(Joueur appartient){
    super(15, appartient,-1, 3, "Tour");
    try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/Tower.png")).getImage();
    } catch (Exception e) {}
  }
}
