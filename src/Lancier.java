import java.awt.*;
import javax.swing.*;


public class Lancier extends Soldat{

	public Lancier(Joueur appartient ){
		super(20, appartient, -6, 2, 1);
		try {
			this.img = new ImageIcon(getClass().getResource("Sprites/images/unitelv2.png")).getImage();
		} catch (Exception e) {}
	}
	public String toString() {
	  return "Lancier";
	}
}
