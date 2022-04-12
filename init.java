import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

public class init {
	//Cette classe pourrait etre dans Game Manager c'est initialiser le jeu avec des position au hazard et le nombre de joueur
	private Terrain t;
	private Joueur[] tabJ;
	private final int[] colors = { 0x9e2703, 0x229c19 ,0x1625a8, 0xe0e330,0x581845,0x16A085 };
	private int nbJ;

	public init (Terrain t, int nbJ) {
	
		this.t = t;
		this.tabJ = new Joueur[nbJ];
		for (int i = 0;i < nbJ; i++) {
			tabJ[i] = new Joueur(colors[i]);
		}
	}
	
	public void initial() {
		for (Joueur j : tabJ) {
			int x = (int)(Math.random()*(t.col-1));
			int y = (int)(Math.random()*(t.row-1));
			
			while (t.tab[x][y].color != 0) {
				x = (int)(Math.random()*(t.col-1));
			    y = (int)(Math.random()*(t.row-1));
				
				}
			t.tab[x][y].setColor(j.getColor());
			t.tab[x][y].etat = new TownHall(1,new Joueur(0),15);
	
			//System.out.println("printed in "+x+ ";" +y );

		}
	}
			
}

