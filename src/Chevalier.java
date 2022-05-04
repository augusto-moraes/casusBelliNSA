import java.awt.*;
import javax.swing.*;


public class Chevalier extends Soldat{

  public Chevalier(Joueur appartient ){
    super(30, appartient, -18, 3, 1 );
    try {
		this.img = new ImageIcon(getClass().getResource("Sprites/images/unitelv3.png")).getImage();
    } catch (Exception e) {}

  }
  public String toString() {
	  return "Chevalier";
  }
}
