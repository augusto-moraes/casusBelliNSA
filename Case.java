import java.util.ArrayList;
import java.io.File;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Case implements Comparable<Case> {
    private static int nbCases = 0;

    public int x;
    public int y;
    private int potentiel;
    public int bcolor;
    public Unite etat;
    private Joueur appartient;
    public int id;
    public int color;
    public boolean fill;
    public Image img;
    
    

    public Case(int x,int y,int color,int bcolor) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.bcolor = bcolor;
		fill = true;
        this.id = nbCases;
        nbCases++;
    }
    
    public Case(int x,int y,int color) {
		this(x,y,color,0xd9d9d9);
	}
    
    public Case(int x, int y) {
		this(x,y,0);
		fill = false;
	}

    public boolean isCaseEmpty() {
        return this.color == 0;
    }
	
	public void setColor(int color) {
		this.color = color;
		fill = true;
	} 
    
    public boolean equals(Object obj){
        Case other = (Case)obj;
        return other.id == this.id;
    }

    public int compareTo(Case other) {
        return this.id >= other.id  ? 1 : -1;
    }

    public String toString() {
        return "jiji " + this.color + " ";
    }
}
