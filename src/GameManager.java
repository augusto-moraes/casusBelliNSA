import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Dimension;
import java.util.Iterator;

public class GameManager {

    public static CopyOnWriteArrayList<Joueur> playersList; // must be CopyOnWriteArrayList to update iterator when removing (avoid CurrentModificationException)
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0x581845, 0x16A085, 0xe0e330 };
    private Affichage affichage; // Cette fenetre c'est le menu
    public FenetreJeu fenetreJeu; // Cela c'est le GUI pendant le jeu
    private Iterator<Joueur> playerIt; 
    private Terrain ter; 
    private Joueur currentPlayer;

	public GameManager() {
        this.playersList = new CopyOnWriteArrayList<Joueur>();
        this.affichage = new Affichage(this,1400,800);
        this.ter = new Terrain(1300,650,29,0,this);
		ter.setPreferredSize(new Dimension(1300, 650));
        ter.addMouseListener(ter);
    }

    public void generatePlayers(int nbJoueurs) {
        for(int i=0;i<nbJoueurs;i++) {
            Joueur aux = new Joueur(colors[i]);
            this.playersList.add(aux);
        }
        this.playerIt = playersList.iterator();
    }

    // génère le terrain avec un TownHall placé aléatoirement pour chaque joueur
    public void generateTerrain() { 
        for (Joueur j : playersList) {
			int x = (int)(Math.random()*(ter.col-1));
			int y = (int)(Math.random()*(ter.row-1));
			
			while (ter.tab[x][y].color != 0) {
				x = (int)(Math.random()*(ter.col-1));
			    y = (int)(Math.random()*(ter.row-1));	
			}
			ter.tab[x][y].setColor(j.getColor());
			ter.tab[x][y].setUnite(new TownHall(1,j,15));
			ter.tab[x][y].setJoueur(j);  
		}
    }

    public Terrain getTerrain() {
		return this.ter;
	}
    
    public FenetreJeu getFeunetreJeu() {
		return this.fenetreJeu;
	}

    public Joueur getCurrentPlayer() {return this.currentPlayer; } 

    // Dans affichage il y a EcouteurLaunch qui lance startGame 
    public void startGame(int nbJoueurs) { 
        affichage.stopMusic();
        affichage.clearScreen();
        this.generatePlayers(nbJoueurs);
        this.generateTerrain();
        this.fenetreJeu = new FenetreJeu(this);
        this.nextPlayer();
        affichage.setContentPane(fenetreJeu);
        affichage.repaint();
        affichage.validate();
    }

    // iterator needs to be updated every time a player is dead
    public void updateIterator() {
        this.playerIt = this.playersList.iterator();
        while(this.playerIt.hasNext() && this.playerIt.next() != this.currentPlayer) {}
    }

    // public void victoire() {
    //     this.affichage.clearScreen();

    // }
    
    public void removePlayer(Joueur joueurMort) {
		this.playersList.remove(joueurMort);
        this.updateIterator();
        if(this.playersList.size() == 1) this.victoire();
	}

    public void nextPlayer() {
        if(!this.playerIt.hasNext()) { this.playerIt = this.playersList.iterator(); }
        this.currentPlayer = this.playerIt.next();
        fenetreJeu.setNextJoueur();
        fenetreJeu.getNextJoueur().initTour();
		fenetreJeu.updateBalance();
        fenetreJeu.repaint();
        fenetreJeu.validate();
    }

    public void victoire() {
        new EcranVictoire(this.currentPlayer, 300, 200);
    }
}
