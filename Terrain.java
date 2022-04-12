import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.*;

public class Terrain extends JPanel implements MouseListener{

    public final int WIDTH ;
    public final int HEIGHT;
    public Case[][] tab; 
    public int Rinscrit;
    public Unite nextUnit;
    public int radius;
    public int col;
    public int row;
    public int fill;
    public Image imgFond;
    public int padding;
    public Graphics2D g;  
    public Case lastSelec;
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330 };

	private GameManager gManager;

    public Terrain(int Width, int Height, int radius,int padding, GameManager gm) {
		this.gManager = gm;

		try {
			imgFond = ImageIO.read(new File("images/Landscape.jpg"));
		} catch (Exception e) {
			System.out.println("Image not found on class Terrain (l.28)");
		}
		this.WIDTH = Width;
		this.HEIGHT = Height;
		this.radius = radius;
		this.padding = padding;
		Rinscrit = (int)Math.round(radius*Math.cos(Math.PI/6));
		
		col = 1 + (int)( WIDTH - 4*radius) /(2*Rinscrit + 2*padding);
		row = 1 + (int)((HEIGHT -3*radius)/(1.5*radius+ 2*Math.round(padding*Math.tan(Math.PI/6))));
		System.out.println(col+","+row);
		tab = new Case[col][row];
		//Cree une tableaux de cases vide sans coordannees x y dans la carte
		for(int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				tab[i][j] = new Case(0,0);
				
			}
		}	
	}
	// Inutile, juste pour tester
	public int  randomColor() {
		int r = (int)(Math.random()*colors.length);
		return colors[r];
	}
	//Utiliser pour la selection et position des troupes
	public void setNextUnit(Unite u) {
		this.nextUnit = u;
	} 
		

	//Detecte le click du mouse et ses coordonnees	
    public void mousePressed(MouseEvent e) {
		Graphics2D g = (Graphics2D)this.getGraphics();
		int x=e.getX(); 
		int y=e.getY();
		changeUnit(x,y);
		this.gManager.nextPlayer();
	}

	//Dessine la figure dans la case correspondante
	public void setUnite(Case c) {
		Graphics2D g = (Graphics2D)this.getGraphics();
		 
		try {
			Image pic = c.etat.getImage();
			g.drawImage(pic,c.x - Rinscrit/2,c.y-Rinscrit/2,Rinscrit,Rinscrit,this);
	 	} catch (Exception e) {
			System.out.println("failed to draw case on Terrain l.73");
	 	}
		 
	}
	
	public void changeUnit(int x,int y) {
		Graphics2D g = (Graphics2D)this.getGraphics();
		//parcour des coordonnees x y graphiques pour voir si ce trouve dans l'hexagone
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) { 
				if (  (Math.pow(tab[i][j].x - x,2)   +  Math.pow(tab[i][j].y - y,2) < Math.pow(Rinscrit,2))) { 

						
					try {
						//La case selectione precedante et change de couleur jaune a gris
						lastSelec.bcolor = 0xd9d9d9;
						drawBorder(g, lastSelec.x,lastSelec.y, lastSelec.bcolor) ;
						
					} catch (Exception e) {}
					//Met la nouvelle nouvelle selection  
					lastSelec = tab[i][j];

					//!!!!COndtion a change super important pour le jeu
					if (tab[i][j].color == 0) {
						//Si la case est disponible, mets l'unite dans la case (nextUnite est update par les actionListener en FenetreJeu)
						tab[i][j].etat = nextUnit;
						//Mettre une couleur au hazard, Inutie pour le jeu 
						int color = gManager.getPlayerColor();
						tab[i][j].setColor(color);
						//Dessine l'hexagone
						Hexagon hex = new Hexagon(tab[i][j].x, tab[i][j].y, radius);				
						hex.draw(g, tab[i][j].x, tab[i][j].y  ,0, color, true);
						//Mets la figure dans l'ecran
						setUnite(tab[i][j]);
						//dessine le bord en couleur gris
						drawBorder(g, tab[i][j].x,tab[i][j].y, tab[i][j].bcolor) ;
						
					}

				}
			
			}
		} 
		try {
			//Cela pourrait etre dedans, apres tout la case clicker est la selected mais la condition impose que c'est pas toujours donc il faut le faire dehors
			lastSelec.bcolor = 0xffff00;
			drawBorder(g, lastSelec.x,lastSelec.y, lastSelec.bcolor) ;
		} catch (Exception e) {
					} 
	}

	public  void mouseEntered(MouseEvent e) {};
	public  void mouseExited(MouseEvent e){};
	public  void mouseClicked(MouseEvent e){};
	public  void mouseReleased(MouseEvent e){};
	 
	 //Fonction du graphics 2D
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.g = g2d;
 
		g.drawImage(imgFond,0,0,WIDTH,HEIGHT,this);
		drawHexGrid(g2d);        
    }  
    //Met en place le tableau en entier , cette methode se repete si on resize la fenetre
	public void drawHexGrid(Graphics g2) {
		g = (Graphics2D) g2;

		int i = 2*radius;
		int element = (int)(1.5*radius) + 2*(int)Math.round(padding*Math.tan(Math.PI/6)) ;
		//Il parcour le tableau de cases et dessine les hexagones dans la position correcte 
		for (int x = 0; x < col; x++) {
			int p = i ;
			int j = 2*radius;

			for (int y = 0; y < row;y++) {
				//Ca c'est pour le decalage entre ligne 1-2
				if ( (j-2*radius)%(2*element) == 0) {
					p = p + Rinscrit + padding;
				} else {
					p = p - Rinscrit - padding;
				}
				Case c = tab[x][y];
				//Enregistre les coordonnes dans le canvas
				c.x = p;
				c.y = j;
				Hexagon hex = new Hexagon(p, j, radius);
				if (c.color != 0) {
					//Les cases qui ont une couleur reste de la couleur)
					hex.draw(g, p, j  , 0, c.color, c.fill);
				}
				if (c.etat != null) {
					//Si ils ont une unite, il dessine la figure correpondante
					g.drawImage(c.etat.getImage(),c.x - Rinscrit/2,c.y-Rinscrit/2,Rinscrit,Rinscrit,this);
				}
				drawBorder(g,p,j,c.bcolor);

				j = j + element;
			}
			i = i + 2*Rinscrit + 2*padding;
		}
	}
	//Cree le bord hexagonale 
	private void drawBorder(Graphics g, int x, int y, int color) {
        Graphics2D g2d = (Graphics2D) g;
        Hexagon hex = new Hexagon(x, y, radius);
		hex.draw(g2d, x, y  , 4, color , false);

	} 	

   /* private void drawHex(Graphics g, int posX, int posY, int x, int y, int r) {
        Graphics2D g2d = (Graphics2D) g;

        Hexagon hex = new Hexagon(x, y, r);
        hex.draw(g2d, x, y, 0, 0xa10d37, true);
        hex.draw(g2d, x, y, 10, 0xffffff, true);
        g.setColor(new Color(0x0e0ec9));
	}*/  	
}
