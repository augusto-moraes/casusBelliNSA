import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.*;

public class Terrain extends JPanel implements MouseListener{

    private final int WIDTH ;
    private final int HEIGHT;
    private Case[][] tab; 
    private int Rinscrit;
    private int radius;
    private int fill;
    private Image imgFond;
    public static int nbcases;
    public int padding;
      

    public Terrain(int Width, int Height, int radius,int padding) {
		try {
			//imgFond = ImageIO.read(new File("image.jpg"));
		} catch (Exception e) {}
		this.WIDTH = Width;
		this.HEIGHT = Height;
		this.radius = radius;
		this.padding = padding;
		Rinscrit = (int)Math.round(radius*Math.cos(Math.PI/6));
		// cree le bon nombre de cases 
		int col = (int)( WIDTH - 4*radius) /(2*Rinscrit + 2*padding);
		
		int row = (int)((HEIGHT -3*radius)/(1.5*radius+ 2*Math.round(padding*Math.tan(Math.PI/6))));
		System.out.println(col + "-"+row);
		nbcases = 0;
		tab = new Case[col+1][row+1];
			for(int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[0].length; j++) {
					nbcases++;
					System.out.println(nbcases);
					//cases vides sans coordonnees
					tab[i][j] = new Case(0,0);
				
				}
			}	
		}
		
		
    public void mousePressed(MouseEvent e) {
		 Graphics2D g = (Graphics2D)this.getGraphics();
    int x=e.getX(); //
    int y=e.getY();
    g.drawOval(x-5,y-5,10,10);
	//change(x,y,this.radius);
	}
	
	public void change(int x,int y,int radius) {

		 Graphics2D g = (Graphics2D)this.getGraphics();
	//	 g.drawOval(x-5,y-5,10,10);
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				// 
			
				if (tab[i][j].color == 0  && (Math.pow(tab[i][j].x - x,2)   +  Math.pow(tab[i][j].y - y,2) < Math.pow(Rinscrit,2))) { 
							
					int color =  0x9e2703;
					tab[i][j].setColor(color);
	
					Hexagon hex = new Hexagon(tab[i][j].x, tab[i][j].y, radius);
					hex.draw(g, tab[i][j].x, tab[i][j].y  ,0, color, true);
					hex.draw(g,tab[i][j].x, tab[i][j].y , fill,0xd9d9d9 , false);
				//	hex.draw(g, tab[i][j].x, tab[i][j].y  ,0, color, true);
				}
			
			}
		} 
	}

//	public void mousePressed(MouseEvent e){};
	public  void mouseEntered(MouseEvent e) {};
	public  void mouseExited(MouseEvent e){};
	public  void mouseClicked(MouseEvent e){};
	public  void mouseReleased(MouseEvent e){};
	

 
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
		g.drawImage(imgFond,0,0,WIDTH,HEIGHT,this);
		drawHexGrid(g2d,radius);
		
		
    //    drawHexGridLoop(g2d, origin, 7, 80, 2);
        
    }
     private void drawHexGrid(Graphics2D g, int radius) {
		 Graphics2D g2d = g;
		
		int tabx = 0;
		int taby = 0; 

		//int padding =  bord;
		
		 for (int i = 2*radius ; i < WIDTH - 2*radius; i = i + 2*Rinscrit + 2*padding) {
			int p = i ;
	
			int element = (int)(1.5*radius) + 2*(int)Math.round(padding*Math.tan(Math.PI/6)) ;
			taby = 0;
			 for (int j = 2*radius; j < HEIGHT - radius ; j= j + element ) {

				 if ( (j-2*radius)%(2*element) == 0) {
					 p = p + Rinscrit + padding;
				 } else {
					 p = p - Rinscrit - padding;
				 }
				  Case c = tab[tabx][taby];
					c.x = p;
					c.y = j;
					
					//g2d.drawOval(c.x-(int)(Rinscrit),c.y-(int)(Rinscrit),2*Rinscrit,2*Rinscrit);
				Hexagon hex = new Hexagon(p, j, radius);
				
				if (c.color != 0) {hex.draw(g2d, p, j  , fill, c.color, c.fill);}
				
				hex.draw(g2d, p, j  , fill,0xd9d9d9 , false);
				taby ++;
			}
			tabx++;
			
		}
	}
				 
				 



   /* private void drawHex(Graphics g, int posX, int posY, int x, int y, int r) {
        Graphics2D g2d = (Graphics2D) g;

        Hexagon hex = new Hexagon(x, y, r);
        hex.draw(g2d, x, y, 0, 0xa10d37, true);
        hex.draw(g2d, x, y, 10, 0xffffff, true);
        g.setColor(new Color(0x0e0ec9));
	}*/  
    
}
