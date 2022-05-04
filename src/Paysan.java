import java.awt.*;
import javax.swing.*;

public class Paysan extends Soldat{

  public Paysan(Joueur appartient){
    super(10, appartient, -2,1,1);
    try {
      this.img = new ImageIcon(getClass().getResource("Sprites/images/unitelv1.png")).getImage();
    } catch (Exception e) {}

  }
  public String toString() {
	  return "Paysan";
  }
}
