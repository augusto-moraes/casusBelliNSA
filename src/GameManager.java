import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;

public class GameManager {

    public static CopyOnWriteArrayList<Joueur> playersList; // must be CopyOnWriteArrayList to update iterator when removing (avoid CurrentModificationException)
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330, 0x581845, 0x16A085 };
    public Affichage affichage;//Cette fenetre c'est le menu
    public FenetreJeu fenetreJeu;//Cela c'estle GUI pendant le jeu
    private Iterator<Joueur> playerIt; // Iterator est l'un des moyens de parcourir (traverse) les éléments d'une Collection.
    private Terrain ter; // Ceci est le terrain de jeu 
    private Joueur currentPlayer;

	public GameManager() {
        this.playersList = new CopyOnWriteArrayList<Joueur>();

        this.affichage = new Affichage(this);
        // Dans affichage il y a EcouteurLaunch qui lance startGame 
        this.ter = new Terrain(1500,700,29,0,this);

    }
    
    public void generatePlayers(int nbJoueurs) { //génère le nombre de joueurs demandés en modifiant l'attribut playerAlive
        for(int i=0;i<nbJoueurs;i++) {
            Joueur aux = new Joueur(colors[i]); // %colors.length
            this.playersList.add(aux);
        }
        this.playerIt = playersList.iterator();
    }

    public void generateTerrain() { // génère le terrain avec un TownHall placé aléatoirement pour chaque joueur
        for (Joueur j : playersList) {
			int x = (int)(Math.random()*(ter.col-1));
			int y = (int)(Math.random()*(ter.row-1));
			
			while (ter.tab[x][y].color != 0) {
				x = (int)(Math.random()*(ter.col-1));
			    y = (int)(Math.random()*(ter.row-1));	
			}
			ter.tab[x][y].setColor(j.getColor());
			ter.tab[x][y].unite = new TownHall(1,j,15);
			ter.tab[x][y].appartient = j;
		}
    }

    public Terrain getTerrain() { return this.ter; }

    public void startGame(int nbJoueurs) { //méthode qui commence le jeu
        this.generatePlayers(nbJoueurs);
        this.fenetreJeu = new FenetreJeu(this);
		this.nextPlayer();
        affichage.setContentPane(fenetreJeu); //affiche la fenetre de jeu, c'est EcouteurLaunch qui clear l'écran précédent
        affichage.repaint();
        affichage.validate();
    }

    public void updateIterator() {
        this.playerIt = this.playersList.iterator();
        while(this.playerIt.hasNext() && this.playerIt.next() != this.currentPlayer) {}
    }
    
    public void removePlayer(Joueur joueurMort) {
		this.playersList.remove(joueurMort);
        this.updateIterator();
	}


    public void nextPlayer() {
        if(!this.playerIt.hasNext()) this.playerIt = this.playersList.iterator();
        this.currentPlayer = this.playerIt.next();
        fenetreJeu.setNextJoueur(this.currentPlayer);
        fenetreJeu.nextJoueur.initTour();
        fenetreJeu.afficheMoney();
		fenetreJeu.afficheRevenus();
		fenetreJeu.updateBalance();
        fenetreJeu.repaint();
        fenetreJeu.validate();
    }
    
    public static int[] getIncomeJoueurs() {
		int[] incomes = new int[playersList.size()];
		int cpt = 0;
		for(Joueur j : playersList) {
			incomes[cpt] = j.retIncome();
			cpt++;
		}
		return incomes;
	}
	
	public static int[] getMoneyJoueurs() {
		int[] argent = new int[playersList.size()];
		int cpt = 0;
		for(Joueur j : playersList) {
			argent[cpt] = j.getMoney();
			cpt++;
		}
		return argent;
	}
}
