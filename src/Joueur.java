import java.util.*;

public class Joueur{
    
    private static int nbJoueur= 0;
    private int id;

    private int color;
    private boolean isAlive;
    private boolean bot;
    private int money; //la monnaie du joueur
    private int nbCase; //le nombre de cases que le joueur a sur le plateau
    public LinkedList<Unite> lesUnites = new LinkedList<Unite>(); //le tableau des unités que possède le joueur sur le terrain

    public Joueur(int color) {
        nbJoueur++;
        this.id=nbJoueur;
        this.color=color;
        this.money= 15;
        this.nbCase=1;
    }

    public String toString() {
		return "Joueur " + this.id + " avec " + getMoney() + " pièces et un nb d'unités de " + lesUnites.size() + " et " + this.nbCase + " cases";
	}

    public void addCase() { //ajoute une case au nombre de cases que le joueur a
		this.nbCase++;
	}

    public void enleveCases(int nb) {
        this.nbCase-=nb;
    }

    public void achatCout(Unite u) { //actualise la monnaie quand on achète une unité
		this.money-=u.getCout();
	}
    
    public int getColor(){
        return this.color;
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
    
    public int getIncome(){ //calcule le revenu des unites du Joueur
        int somme=this.nbCase;
        for ( Unite a : lesUnites) {
            somme+= a.getIncome();
  
        }
        return somme; 
    }
    
    public boolean initTour(){
        // récupérer son économie
        while(lesUnites.size() != 0 && this.money+this.getIncome()<=0) { //tant qu'on a la somme de la monnaie et des incomes qui est négative, on tue la dernière unite du tableau d'unites
            this.lesUnites.removeLast().Case.unite = null;
        }
        this.money += this.getIncome();
        
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
