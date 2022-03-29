import java.util.*;

public class Graph {
    private int nbSommets, nbArretes;
    private ArrayList<Pair> adj;
    private Case[] sommets;

    public Graph(int sommets, int arretes) {
        this.nbSommets = sommets;
        this.nbArretes = arretes;

        this.adj = new ArrayList<Pair>();
        this.sommets = new Case[nbSommets];

        this.generateSommets();
        this.bondSommets();

        System.out.println(adj);
    }

    public void generateSommets() {
        for(int i=0; i<nbSommets; i++) {
            sommets[i] = new Case();
        }
    }

    public void bondSommets() {
        int i; int j;
        while(adj.size() < nbArretes + (nbSommets - 1)){
            i = (int)(Math.random() * nbSommets);
            j = (int)(Math.random() * nbSommets);
            if(i!=j)adj.add(new Pair(sommets[i],sommets[j]));
        }
    }
} 
