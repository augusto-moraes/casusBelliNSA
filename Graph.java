import java.util.*;

public class Graph {
    private int nbSommets, nbArretes;
    private TreeSet<Pair> adj;
    private Case[] sommets;

    public Graph(int sommets, int arretes) {
        this.nbSommets = sommets;
        this.nbArretes = arretes;

        this.adj = new TreeSet<Pair>();
        this.sommets = new Case[nbSommets];

        this.generateSommets();
        this.bondSommets();
    }

    public void generateSommets() {
        for(int i=0; i<nbSommets; i++) {
            sommets[i] = new Case(0,0);
        }
    }

    public void bondSommets() {
        int i; int j;
        while(adj.size() < nbArretes){
            i = (int)(Math.random() * nbSommets);
            j = (int)(Math.random() * nbSommets);
            if(i!=j) {
                Pair aux = new Pair(sommets[i],sommets[j]);
                //if(!adj.contains(aux)) 
                adj.add(aux);
            }
        }
    }

    public String toString() {
        String res = "";
        for(Pair p : adj) {
            res+= (p.toString() + " ");
        }
        return res;
    }
} 
