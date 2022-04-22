import java.util.LinkedList;
import java.util.Iterator;

public class GameManager {

    public static LinkedList<Joueur> playersList;
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330, 0x581845, 0x16A085 };
    private int playersAlive;
    private Affichage affichage;
    private FenetreJeu fenetreJeu;
    private Iterator<Joueur> playerIt;
    private int nextPlayerColor;
    private Terrain ter;

	public GameManager() {
        this.playersList = new LinkedList<Joueur>();

        this.affichage = new Affichage(this);
        
        this.ter = new Terrain(1500,700,29,0,this);

        this.nextPlayerColor = 0;
    }

    public void setPlayersAlive(int n) {
        this.playersAlive = n;
    }

    public void generatePlayers(int nbJoueurs) {
        this.setPlayersAlive(nbJoueurs);
        for(int i=0;i<nbJoueurs;i++) {
            Joueur aux = new Joueur(colors[i]); //%colors.length
            this.playersList.add(aux);
        }
        this.playerIt = playersList.iterator();
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
			ter.tab[x][y].unite = new TownHall(1,j,15);
			ter.tab[x][y].appartient = j;
		}
    }

    public Terrain getTerrain() { return this.ter; }

    public void startGame(int nbJoueurs) {
        this.generatePlayers(nbJoueurs);
        
        this.fenetreJeu = new FenetreJeu(this);
		this.nextPlayer();
        affichage.setContentPane(fenetreJeu);
        affichage.repaint();
        affichage.validate();
    }

    public int getPlayerColor() { return this.nextPlayerColor; }

    public void nextPlayer() {
        if(!this.playerIt.hasNext()) this.playerIt = this.playersList.iterator();
        Joueur elem = this.playerIt.next();
        fenetreJeu.setNextJoueur(elem);

        this.nextPlayerColor = elem.getColor();
        fenetreJeu.repaint();
        fenetreJeu.validate();
    }
    
    public static int[] getIncomeJoueurs() {
		int[] incomes = new int[playersList.size()];
		int cpt = 0;
		for(Joueur j : playersList) {
			for(Unite u : j.lesUnites) {
				incomes[cpt] = u.getIncome();
			}
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
