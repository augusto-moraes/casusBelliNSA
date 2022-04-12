import java.util.LinkedList;
import java.util.Iterator;

public class GameManager {

    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330, 0x581845, 0x16A085 };

    private LinkedList<Joueur> playersList;

    private int playersAlive;

    private Affichage affichage;
    private FenetreJeu fenetreJeu;

    private Iterator<Joueur> playerIt;
    private Joueur first;

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
        this.playerIt = playersList.iterator();
        this.first = playerIt.hasNext() ? playerIt.next() : null;
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

    public Terrain getTerrain() {return this.ter;}

    public void startGame(int nbJoueurs) {
        this.generatePlayers(nbJoueurs);
        this.fenetreJeu = new FenetreJeu(this);

        affichage.setContentPane(fenetreJeu);
        affichage.repaint();
        affichage.validate();

        //this.gameLoop();
    }

    public void nextPlayer() {
        Joueur elem = this.playerIt.hasNext() ? this.playerIt.next() : this.first;
        fenetreJeu.setNextJoueur(elem);
        System.out.println(elem+" turns");
    }

    // public void gameLoop() {
    //     while(this.playersAlive > 1) {
    //         for(Joueur joueur : this.playersList) {
    //             fenetreJeu.setNextJoueur(joueur);
    //         }
    //     }
    // }
}

