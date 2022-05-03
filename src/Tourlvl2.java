import java.awt.*;
import javax.swing.*;

public class Tourlvl2 extends Batiment {
	
  public Tourlvl2(Joueur appartient){		
		super(35, appartient,-6, 4, "Tour");
		try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/Strong_tower.png")).getImage();
    } catch (Exception e) {}
  }
}
