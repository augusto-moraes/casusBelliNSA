import java.awt.*;
import javax.swing.*;

public class Unite {

    private int cout;
    public Joueur appartient;
    private int income;
    //Essai une image par defaut pour pouvoir utiliser getImage de clases filles !! (Dans constructeur)
    final public Image img =  null;
  
    public Unite(int cout, Joueur appartient, int income){
        this.cout=cout;
        this.appartient= appartient;
        this.income=income;
    }
   
	public int getCout() {
		return cout;
	}
    
    public int getIncome(){
        return income;
    }
    
    public Image getImage() {
	  return img;
  }


}
