import java.awt.*;
import javax.swing.*;

public class Unite {
	//C'est peut etre de la merde mais je vais mettre la case qu'il appartient dans l'unite
	private Case Case;
    private int niveau;
    private int cout;
    private Joueur appartient;
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
	public Case getCase() {
		return this.Case;
	}
	
	 public Joueur getJoueur() {
		return appartient;
	}
	public void setJoueur(Joueur j) {
		this.appartient = j;
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
