import java.awt.*;
import javax.swing.*;

public class Unite {
	//C'est peut etre de la merde mais je vais mettre la case qu'il appartient dans l'unite
	public Case Case;
    private int niveau;
    private int cout;
    public Joueur appartient;
    private int income;
    public Image img;
  
    public Unite(int cout, Joueur appartient, int income,int niveau){
        this.cout=cout;
        this.appartient= appartient;
        this.income=income;
        this.niveau=niveau;
        this.Case = null;
        this.img = null;
    }
    
    public void setCase(Case c) {
		this.Case = c;
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
    public int getNiveau(){
        return niveau;
    }

}
