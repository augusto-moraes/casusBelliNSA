import java.awt.*;
import javax.swing.*;

public class Paladin extends Soldat{

  public Paladin(Joueur appartient ){
    super(40, appartient, -36, 4, 1 );
    try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/unitelv4.png")).getImage();
    } catch (Exception e) {}

  }
  public String toString() {
	  return "Paladin";
  }
}
