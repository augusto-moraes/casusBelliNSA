import java.util.ArrayList;

public class Case implements Comparable<Case> {
    private static int nbCases = 0;

    // private int x;
    // private int y;
    private int potentiel;
    private Unite etat;
    private Joueur appartient;
    public int id;

    public Case() {
        this.id = nbCases;
        nbCases++;
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
