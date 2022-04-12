import java.awt.*;
import javax.swing.*;

public class Unite {

    private int cout;
    private Joueur appartient;
    private int income;
    //Essai une image par defaut pour pouvoir utiliser getImage de clases filles
    final public Image img =  new ImageIcon(getClass().getResource("images/Tower.png")).getImage();
  
    public Unite(int cout, Joueur appartient, int income){
        this.cout=cout;
        this.appartient= appartient;
        this.income=income;
   

    }
    
    public int getIncome(){
        return income;
    }
    public Image getImage() {
	  return img;
  }


}
