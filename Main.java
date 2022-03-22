import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JPanel implements MouseListener{

    private final int WIDTH = 1500;
    private final int HEIGHT = 700;
    private Type[][] tab;
    private int k= 50; 
    private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330 };
      

    public Main() {
        JFrame f = new JFrame();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        f.setContentPane(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.addMouseListener(this);
    }
    public void mousePressed(MouseEvent e) {
    int x=e.getX()-10;
    int y=e.getY()-40;
    
 //   System.out.println(x+","+y);//these co-ords are relative to the component
    change(x,y,k);
	}
	
	public int  randomColor() {
		int r = (int)(Math.random()*colors.length);
		return colors[r];
	}
		
	public void change(int x,int y,int radius) {
		 Graphics2D g = (Graphics2D)this.getGraphics();
		for (int i = 0; i < ( WIDTH ) /(2*radius) + 1; i++) {
			for (int j = 0; j < (HEIGHT )/(2*radius)+1; j++) {
				if (tab[i][j] == null) {} else{ 
			//	if (tab[i][j].colour == 0 && (Math.pow(tab[i][j].x - x,2)   +  Math.pow(tab[i][j].y - y,2) < Math.pow(radius,2))) { 
				if (tab[i][j].colour == 0 && tab[i][j].inside(x,y))  { 
					int color = randomColor();
					tab[i][j].colour = color;
				//	System.out.println("yes");
					Hexagon hex = new Hexagon(tab[i][j].x, tab[i][j].y, radius);
					hex.draw(g, tab[i][j].x, tab[i][j].y  ,0, color, true);
				}
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
        Point origin = new Point(WIDTH / 2, HEIGHT / 2);

		drawHexGrid(g2d,k, 0);
    //    drawHexGridLoop(g2d, origin, 7, 80, 2);
        
    }
     private void drawHexGrid(Graphics g, int radius, int padding) {
		 Graphics2D g2d = (Graphics2D) g;
	
		int col = ( WIDTH ) /(2*radius);
		int row = (HEIGHT )/(2*radius);
		tab = new Type[col+10][row+10];
		int tabx = 0;
		int taby = 0; 
		int espace = (int)(2*radius*Math.cos(Math.PI/6));
		
		//System.out.println(col + "," +row);
	
	
		 for (int i = 2*radius ; i < WIDTH - radius; i = i + 2*radius + padding) {
			 
			 
			 int p = i ;
			int  correction = (int)(padding*1.7);
			int element = (int)((1+Math.cos(Math.PI/6) )*radius)  - correction;
			taby = 0;
			 for (int j = 2*radius; j < HEIGHT - radius ; j= j + element ) {
				
				//System.out.println(j);
				 
				 int color = 0xd9d9d9;
//				 	System.out.println(j + " : " + (j-2*radius)%(2*element));
				 if ( (j-2*radius)%(2*element) == 0) {
	
					 p = p + radius;
				 } else {
					 p = p - radius;
				 }
				  tab[tabx][taby] = new Type(p,j,radius);
			//	System.out.println(p + "," +j);
				//System.out.println("Types " + tab[tabx][taby].x + "," +tab[tabx][taby].y);
				Hexagon hex = new Hexagon(p, j, radius);
				hex.draw(g2d, p, j  , 0, color, false);
				taby ++;
			}
			tabx++;
			
		}
	}
				 
				 



    private void drawHex(Graphics g, int posX, int posY, int x, int y, int r) {
        Graphics2D g2d = (Graphics2D) g;

        Hexagon hex = new Hexagon(x, y, r);
        hex.draw(g2d, x, y, 0, 0xa10d37, true);
        hex.draw(g2d, x, y, 4, 0xffffff, false);
        g.setColor(new Color(0x0e0ec9));
    }
    
}
