import java.util.ArrayList;

public class Case implements Comparable<Case> {
    private static int nbCases = 0;

    public int x;
    public int y;
    private int potentiel;
//    private Unite etat;
  //  private Joueur appartient;
    public int id;
    public int color;
    public boolean fill;

    public Case(int x,int y,int color) {
		this.x = x;
		this.y = y;
		this.color = color;
		fill = true;
        this.id = nbCases;
        nbCases++;
    }
    
    public Case(int x, int y) {
		this(x,y,0);
		fill = false;
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
        return " " + this.id + " ";
    }
}
