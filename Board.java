import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.*;

public class Board extends JPanel implements MouseListener{

    private final int WIDTH ;
    private final int HEIGHT;
    private Cases[][] tab;
    private int k= 50; 
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330 };
    private int Rinscrit;
    private int radius;
    private int fill;
    private Image imgFond;
      

    public Board (int Width, int Height) {
		try {
			imgFond = ImageIO.read(new File("image.jpg"));
		} catch (Exception e) {}
		radius = k;
		this.WIDTH = Width;
		this.HEIGHT = Height;
		Rinscrit = (int)Math.round(radius*Math.cos(Math.PI/6));
		
		//Creation du tableau 
		int col = ( WIDTH ) /(2*Rinscrit);
		int row = (HEIGHT )/(2*radius);
		tab = new Cases[col+10][row+10];
		for(int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				//cases vides sans coordonnees
				tab[i][j] = new Cases(0,0,0,false);
				
			}
		}


				
		
		}
    public void mousePressed(MouseEvent e) {
    int x=e.getX()-10; //
    int y=e.getY()-55;
    //g2d.drawOval(x-radius,y-radius,2*radius,2*radius);
	//Changer la couleur pres de x,y 
    change(x,y,radius);
	}
	
	public int  randomColor() {
		int r = (int)(Math.random()*colors.length);
		return colors[r];
	}
		
	public void change(int x,int y,int radius) {

		 Graphics2D g = (Graphics2D)this.getGraphics();
	//	 g.drawOval(x-5,y-5,10,10);
		for (int i = 0; i < ( WIDTH ) /(2*radius) + 1; i++) {
			for (int j = 0; j < (HEIGHT )/(2*radius)+1; j++) {
				// 
			
				if (tab[i][j].color == 0  && (Math.pow(tab[i][j].x - x,2)   +  Math.pow(tab[i][j].y - y,2) < Math.pow(Rinscrit,2))) { 
							
					int color = randomColor();
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
		drawHexGrid(g2d,k, 0);
		
		
    //    drawHexGridLoop(g2d, origin, 7, 80, 2);
        
    }
     private void drawHexGrid(Graphics2D g, int radius, int bord) {
		 Graphics2D g2d = g;
		
		int tabx = 0;
		int taby = 0; 
		int fill = 0;
		int padding = (int)(fill/2) + bord;
		
		 for (int i = 2*radius ; i < WIDTH - 2*radius; i = i + 2*Rinscrit + 2*padding) {
			int p = i ;
			int  correction = (int)(padding*1.7);
			int element = (int)(1.5*radius) + 2*(int)Math.round(padding*Math.tan(Math.PI/6)) ;
			taby = 0;
			 for (int j = 2*radius; j < HEIGHT - radius ; j= j + element ) {

				 if ( (j-2*radius)%(2*element) == 0) {
					 p = p + Rinscrit + padding;
				 } else {
					 p = p - Rinscrit - padding;
				 }
				  Cases c = tab[tabx][taby];
					c.x = p;
					c.y = j;
					
					//g2d.drawOval(c.x-(int)(Rinscrit),c.y-(int)(Rinscrit),2*Rinscrit,2*Rinscrit);
				Hexagon hex = new Hexagon(p, j, radius);
				
				if (c.color != 0) {hex.draw(g2d, p, j  , fill, c.color, c.taken);}
				
				hex.draw(g2d, p, j  , fill,0xd9d9d9 , false);
				taby ++;
			}
			tabx++;
			
		}
	}
				 
				 



    private void drawHex(Graphics g, int posX, int posY, int x, int y, int r) {
        Graphics2D g2d = (Graphics2D) g;

        Hexagon hex = new Hexagon(x, y, r);
        hex.draw(g2d, x, y, 0, 0xa10d37, true);
        hex.draw(g2d, x, y, 10, 0xffffff, true);
        g.setColor(new Color(0x0e0ec9));
    }
    
}
