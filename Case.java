public class Case {
    private static nbCases = 0;

    private int x;
    private int y;
    private int potentiel;
    private Unite etat;
    private Joueur appartient;
    private Case[] voisins;

    public Case() {
        this.nbCases++;
        this.voisins = new Case[6];

        if(nbCases < 50) this.generateVoisins(0.5);
    }

    private generateVoisins(double probaVoisin) {
        for(Case it : voisins) {
            it = Math.random() < probaVoisin ? new Case() : null;
        }
    }
}
