import java.awt.*;
import javax.swing.*;


public class Mine extends Batiment {

    public Mine(Joueur appartient) {
		super(12, appartient,4, 1, "Mine");
		try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/Farm.png")).getImage();
    } catch (Exception e) {}
  }
}
