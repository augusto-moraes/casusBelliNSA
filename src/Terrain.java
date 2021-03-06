import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.*;

public class Terrain extends JPanel implements MouseListener{

    private final int WIDTH ; //largeur
    private final int HEIGHT; //hauteur
    public Case[][] tab;   //Tableau avec les unites 
    private int Rinscrit;	//rayon cercle inscrit de l'hexagone
    private Unite nextUnit; //unité qu'on a sélectionné dans la barre d'unités

    public int radius; //Rayon de l'hexagone externe
    public int col; //colonne
    public int row; //ligne

    private Image imgFond; //Image de fond
    private int padding; //remplissage
    private Graphics2D g;
    private Case lastSelec; //Accesoire pour selectioner 
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330 };
    private GameManager manager;
    private boolean transition; //Pour voir si c'est un deplacement d'une case

    

    public Terrain(int Width, int Height, int radius,int padding, GameManager gm) {
		this.manager = gm;
		try {
			imgFond = ImageIO.read(new File("Sprites/images/Landscape.jpg"));
		} catch (Exception e) {}
		this.WIDTH = Width;
		this.HEIGHT = Height;
		this.radius = radius;
		this.padding = padding;
		
		Rinscrit = (int)Math.round(radius*Math.cos(Math.PI/6));
		//Dimension pour que tout rentre dans la fenetre correctement
		col = 1 + (int)( WIDTH - 4*radius) /(2*Rinscrit + 2*padding);
		row = 1 + (int)((HEIGHT -3*radius)/(1.5*radius+ 2*Math.round(padding*Math.tan(Math.PI/6))));
		tab = new Case[col][row];
		//Cree un tableau de cases vide sans coordonnees x y dans la carte
		for(int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				tab[i][j] = new Case(0,0);		
			}
		}	
	}
	public void setTransition(boolean vec) {
		this.transition = vec;
	}
	
	public Unite getNextUnit() {
		return nextUnit;
	}
	//Effacer un unite u du tableau (elimine l'image en remplissant l'hexagone (la couleur est dans la case qui aurait etait changer avant d'execute cette methode))
	public void effacer(Unite u) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) { 
				if (u.equals(tab[i][j].getUnite())) {
					doHex(g,tab[i][j]);
					break;
				}
			}
		}
	}
					
	
	public boolean PossiblePlace(int x,int y) {
		boolean possible = false;
		// On peut que deplacer si le prochain unite n'est pas null
		if (nextUnit != null && (transition == true ||  (nextUnit.getJoueur().getMoney() - nextUnit.getCout()) >= 0)) {
			// Si l'unité est un batiment, et la case est du meme joueur que le bat et la case n'as pas d'unité
			if (nextUnit instanceof Batiment && tab[x][y].getJoueur() == nextUnit.getJoueur() && tab[x][y].getUnite() == null) {
				possible = true;
				//D'autre part si c'est un soldat et une Case Voisine est de la même couleur que la prochaine unite 
				//On mesure les differences de puissance 
				//Je suis presque sure que checkvoisin et checkvoisintour pourraient aller ensemble
			} else if (nextUnit instanceof Soldat &&  checkVoisin(x,y)) {
				if ( checkVoisinTour(x,y,nextUnit)){
                    
                    if (tab[x][y].getUnite() != null && tab[x][y].getJoueur() != nextUnit.getJoueur() && nextUnit.getNiveau()>=tab[x][y].getUnite().getNiveau()) { /*
                        si la case contient une unité qui n'est pas de la couleur du joueur dont c'est le tour
                        * on supprime l'unité déjà présente sur la case (donc l'unité du joueur attaqué)
                        * on rajoute la case dans les appartenances du Joueur (donc le Joueur offensif a gagné
                        * une case), et on retourne true*/
                        if (tab[x][y].getUnite() instanceof TownHall) {
                            Joueur jp = tab[x][y].getJoueur();
                            manager.removePlayer(jp);
                            for (int i = 0; i < tab.length; i++) {
                                for (int j = 0; j < tab[0].length; j++) { 
                                    if (tab[i][j].getJoueur() == jp) {
                                        tab[i][j] = new Case(tab[i][j].x,tab[i][j].y);
                                    }
                                }
                            }
                            this.repaint();
                            this.validate();
                        }
                        else {
                            tab[x][y].getJoueur().getListUnites().remove(tab[x][y].getUnite());
                            tab[x][y].getJoueur().enleveCases(1);
                        }
                        nextUnit.getJoueur().addCase(); 
                        possible = true;
                    } else if (tab[x][y].getUnite() == null) { //si pas d'unité sur la case, on la prend direct
                        if (tab[x][y].getJoueur() == null) {
                            nextUnit.getJoueur().addCase();
                        }
                        possible = true;
                    }
                }
				
			}
		} return possible; 
	}

	public boolean checkVoisin(int x, int y) {
		int [] directions = {-1,0,1};

		for(int i : directions) {
			for(int j : directions) {
				if(!((y%2 != 0 && i == 1 && j!=0) || (y%2 == 0 && i == -1 && j!=0))) {
					if(x+i>=0 && x+i<tab.length && y+j>=0 && y+j<tab[0].length && tab[x+i][y+j].getJoueur() != null 
					&& tab[x+i][y+j].getJoueur().getId() == nextUnit.getJoueur().getId())
						return true;
				}
			}
		}

		return false;
	}
    //Permet de regarder si la case attaquée possède un voisin qui est une tour de trop haut niveau, si oui alors return false. Sinon, l'attaque est possible, return True
    public boolean checkVoisinTour(int x, int y, Unite unite) {
		int [] directions = {-1,0,1};
		boolean res  = true;

		for(int i : directions) {
			for(int j : directions) {
				if(!((y%2 != 0 && i == 1 && j!=0) || (y%2 == 0 && i == -1 && j!=0))) {
					if(x+i>=0 && x+i<tab.length && y+j>=0 && y+j<tab[0].length && tab[x+i][y+j].getJoueur() != null 
					&& (tab[x+i][y+j].getUnite() instanceof Tour || tab[x+i][y+j].getUnite() instanceof Tourlvl2 || tab[x+i][y+j].getUnite() instanceof Paladin ) && tab[x+i][y+j].getUnite().getNiveau()> unite.getNiveau() && tab[x+i][y+j].getJoueur() != nextUnit.getJoueur())
						res = false;
				}
			}
		}

		return res;
	}
		
	//Utiliser pour la selection et position des troupes
	public void setNextUnit(Unite u) {
		this.nextUnit = u;
	} 
		

	//Detecte le click de la souris et de ses coordonnees	
    public void mousePressed(MouseEvent e) {
		 Graphics2D g = (Graphics2D)this.getGraphics();
		int x=e.getX(); //
		int y=e.getY();
		Clicked(x,y);

	}
	//Dessine la figure dans la case correspondante
	public void setUnite(Case c) {
		Graphics2D g = (Graphics2D)this.getGraphics();
		c.getUnite().setCase(c);
		try {
			Image pic = c.getUnite().getImage();
			g.drawImage(pic,c.x - Rinscrit/2,c.y-Rinscrit/2,Rinscrit,Rinscrit,this);
		} catch (Exception e) {} 
	}
	
	public void Clicked(int x,int y) {
		Graphics2D g = (Graphics2D)this.getGraphics();
		//parcours des coordonnees x y graphiques pour voir si se trouve dans l'hexagone
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) { 
				if ((Math.pow(tab[i][j].x - x,2) + Math.pow(tab[i][j].y - y,2) < Math.pow(Rinscrit,2))) { 
					if (lastSelec != null) {
						//La case selectione precedente et change de couleur jaune a gris
						lastSelec.setBcolor( 0xd9d9d9);
						drawBorder(g, lastSelec) ;
					} 
					//Test si on click dans une case avec un soldat de notre color
					if (tab[i][j].getUnite() != null && tab[i][j].getUnite() instanceof Soldat  && nextUnit.getJoueur() == tab[i][j].getJoueur()) {
						//Si le soldat peut de deplacer mettre en nextUnit le soldat et true pour transition (Pour l'efface apres)
						if (((Soldat)tab[i][j].getUnite()).getdeplacement() == true) {
							nextUnit = tab[i][j].getUnite();
							transition = true;
							
						} else {//Si on click sur un de nos soldat il se passe rien () le nextUnit devient un StandBy
							 nextUnit = new Unite(0,nextUnit.getJoueur(),0,0);
							 transition = false;
							 
							
						}
						
					} else if (PossiblePlace(i,j)) { 
							
						//Si la case est disponible, mets l'unite dans la case (nextUnite est update par les actionListener dans FenetreJeu)
						tab[i][j].setUnite(nextUnit);
						
						//Met dans la case le joueur qui la possede
						tab[i][j].setJoueur(nextUnit.getJoueur());
						//Met la couleur du joueur dans la case
						tab[i][j].setColor(nextUnit.getJoueur().getColor());
						//Dessine l'hexagone
						doHex(g,tab[i][j]);
						//Mets la figure dans l'ecran
						setUnite(tab[i][j]);
						//dessine le bord en couleur gris
						drawBorder(g, tab[i][j]) ;
						//Si c'est un changement eliminer la possibilite de bouger a nouveau au soldat et effacer la case (Peindre sur la figure)  
						if (transition) {
							((Soldat)tab[i][j].getUnite()).setdeplacement(false);
								lastSelec.setUnite(null);
								//Ce lastSelec c'est le meme id que tab[][] precedant dont on efface l'unite 
								doHex(g,lastSelec);
								transition = false; 
								

						} else {
							//et ajoute la piece que si ce n'est pas une transition
							nextUnit.getJoueur().getListUnites().add(nextUnit);
							//	if (!nextUnit.getJoueur().getListUnites().contains(nextUnit)) {
							nextUnit.getJoueur().achatCout(nextUnit);
							//}
					
						}
						//Mettre un StandBy pour obliger à clicker sur le bouton de 
						//nouveau pour creer une nouvelle unite (sinon meme unite dans toute les cases)
						nextUnit = new Unite(0,nextUnit.getJoueur(),0,0);
							
					} else {
						//Si c'est pas possible un deplacement ni mettre une unite, faire un stanby total
						transition = false;
						nextUnit = new Unite(0,nextUnit.getJoueur(),0,0);
						}
					lastSelec = tab[i][j];
					this.manager.fenetreJeu.updateBalance();					
				} 					
			}
		}		 
			//Couleur Jaune a la case selectioner
			lastSelec.setBcolor(0xffff00);
			drawBorder(g, lastSelec) ;					
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
		if (lastSelec != null) {
			drawBorder(g2d, lastSelec) ;	     
		}
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
				if (c.color != 0) {
					//Les cases qui ont une couleur reste de la couleur)
					doHex(g,c);
					//hex.draw(g, p, j  , 0, c.color, c.fill);
				}
				if (c.getUnite() != null) {
					//Si ils ont une unite, il dessine la figure correpondante
					g.drawImage(c.getUnite().getImage(),c.x - Rinscrit/2,c.y-Rinscrit/2,Rinscrit,Rinscrit,this);
				}
				drawBorder(g,c);

				j = j + element;
			}
			i = i + 2*Rinscrit + 2*padding;
		}
	}
	
	
	//Cree le bord hexagonal
	 private void drawBorder(Graphics2D g,Case c) {
        Hexagon hex = new Hexagon(c.x, c.y, radius);
		hex.draw(g , 4, c.getBcolor() , false);

	}
	//dessine l'hexagone
	private void doHex(Graphics2D g,Case c) {
		Hexagon hex = new Hexagon(c.x,c.y, radius);
		hex.draw(g, 0, c.color, c.fill);
		drawBorder(g,c);
	}
}
