// setNextJoueur() en fenetreJeu et setter le joueur depuis GameManager

import java.util.LinkedList;

public class GameManager {

    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330, 0x581845, 0x16A085 };

    private LinkedList<Joueur> playersList;

    private int playersAlive;

    private Affichage affichage;
    private FenetreJeu fJeu;

    private Terrain ter;

	public GameManager() {
        this.playersList = new LinkedList<Joueur>();

        this.affichage = new Affichage(this);
        this.ter = new Terrain(1500,700,29,0);

    }

    public void setPlayersAlive(int n) {
        this.playersAlive = n;
    }

    public void generatePlayers(int nbJoueurs) {
        this.setPlayersAlive(nbJoueurs);
        for(int i=0;i<nbJoueurs;i++) {
            Joueur aux = new Joueur(colors[i%colors.length]);
            this.playersList.add(aux);
        }
    }

    public void generateTerrain() {
        for (Joueur j : playersList) {
			int x = (int)(Math.random()*(ter.col-1));
			int y = (int)(Math.random()*(ter.row-1));
			
			while (ter.tab[x][y].color != 0) {
				x = (int)(Math.random()*(ter.col-1));
			    y = (int)(Math.random()*(ter.row-1));
				
			}
			ter.tab[x][y].setColor(j.getColor());
			ter.tab[x][y].etat = new TownHall(1,new Joueur(0),15);
		}
    }

    public void startGame(int nbJoueurs) {
        this.generatePlayers(nbJoueurs);
        this.fJeu = new FenetreJeu(nbJoueurs);

        affichage.setContentPane(fJeu);
        affichage.repaint();
        affichage.validate();
    }

    public void gameLoop() {
        while(this.playersAlive > 1) {
            for(Joueur joueur : this.playersList) {
                //wait for this player to play
            }
        }
    }
}

