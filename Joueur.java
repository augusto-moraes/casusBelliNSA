import java.util.*;

public class Joueur{
    
    private String nom;
    private static int nbJoueur= 0;
    
    private int id;
    private int color;
    private boolean isAlive;
    private boolean bot;
    private int money;
    private int nbCase;
    private LinkedList<Unite> lesUnites = new LinkedList<Unite>();

    
    public Joueur(String nom, int color) {
        
        nbJoueur++;
        this.id=nbJoueur;
        this.nom=nom;
        this.color=color;
        this.money= 15;
        this.nbCase=7;
        
    }
    
    public Joueur(int color) {
        this("", color);
    }
    
    public int getColor(){
        return color;
    }
    
    public String getNom(){
        return nom;
        
    }
    public int getMoney(){
        return money;
    }
    
    public int getId(){
        return id;
    }
    
    public int getnbCase(){
        return nbCase;
    }
    
    public void setnbCase(int nbcases){
        this.nbCase=nbcases;
    }
    
    public int retIncome(){
        int somme=0;
        for ( Unite a : lesUnites) {
            somme+= a.getIncome();
            
        }
        return somme;
            
    }
    
    public boolean initTour(){
        
        // récupérer son économie
        this.money += this.retIncome();
        this.money +=this.nbCase;
        //
        return true;   
    }   

	public String toString() {
		return ("Player " + this.id);
	} 
}
