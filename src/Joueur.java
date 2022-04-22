import java.util.*;

public class Joueur{
    
    private static int nbJoueur= 0;
    private String nom;
    private int id;
    private int color;
    private boolean isAlive;
    private boolean bot;
    private int money;
    private int nbCase;
    public LinkedList<Unite> lesUnites = new LinkedList<Unite>();

    // nom un peu useless, on pourrait peut-etre enlever si on l'utilise pas a la fin
    public Joueur(String nom, int color) {
        nbJoueur++;
        this.id=nbJoueur;
        this.nom=nom;
        this.color=color;
        this.money= 15;
        this.nbCase=1;
    }

    public String toString() {
		return "Joueur " + this.id + " avec " + getMoney() + " balles et un nb d'unites de " + lesUnites.size() + " et " + this.nbCase + " cases";
	}
    
    public Joueur(int color) {
        this("Joueur", color);
    }

    public void addCase() {
		this.nbCase++;
	}

    public void enleveCases(int nb) {
        this.nbCase-=nb;
    }

    public void achatCout(Unite u) {
		this.money-=u.getCout();
	}
    
    public int getColor(){
        return this.color;
    }
    
    public String getNom(){
        return this.nom;   
    }

    public int getMoney(){
        return this.money;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int getnbCase(){
        return this.nbCase;
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
        this.money += this.nbCase;
        while(this.money+this.retIncome()<=0) {
            this.lesUnites.removeLast();
        }
        this.money += this.retIncome();
        
        for(Unite a : lesUnites) {
            if (a instanceof Soldat) {
                Soldat b;
                b= (Soldat) a;
                b.setdeplacement(true);
            }
        }
        return true;
    } 
}
