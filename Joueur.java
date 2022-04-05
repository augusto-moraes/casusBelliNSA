import java.util.*;

public class Joueur{
    
    private String nom;
    private static int nbJoueur= 0;

    private String color;
    private boolean isAlive;
    private boolean bot;
    private int money;
    private int nbCase;
    private LinkedList<Unite> lesUnites = new LinkedList<Unite>();

    
    public Joueur(String nom, String color) {
        nbJoueur ++;
        
        this.nom=nom;
        this.color=color;
        this.money= 15;
        this.nbCase=7;
    }
    
    public String getcolor(){
        return color;
    }
    
    public String getnom(){
        return nom;
        
    }
    public int getMoney(){
        return money;
    }
    
    public int getnbCase(){
        return nbCase;
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
        
        //

        
    return true;
        
    }
    
    
    
}
