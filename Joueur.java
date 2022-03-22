public class Joueur{
    private String nom;
    private static int nbJoueur= 0;
    
    private int id;
    private boolean isAlive;
    private boolean bot;
    public Joueur(String nom) {
        nbJoueur ++;
        id=nbJoueur;
    }

}
