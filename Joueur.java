public class Joueur{
    
    private String nom;
    private static int nbJoueur= 0;
    private int id;
    private String color;
    private boolean isAlive;
    private boolean bot;
    
    public Joueur(String nom, String color) {
        nbJoueur ++;
        id=nbJoueur;
        this.nom=nom;
        this.color=color;
    }
    
    public String getcolor(){
        return color;
    }
    
    public int getid(){
        return id;
    }
    
    public String getnom(){
        return nom;
    }
    
    
    
    
}
