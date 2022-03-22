public class Terrain {
    private int taille;
    private Case[] carte;

    public Terrain(int taille) {
        this.taille = taille;
        this.carte = new Case[taille];
    }
}