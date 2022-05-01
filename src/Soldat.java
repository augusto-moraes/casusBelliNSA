public class Soldat extends Unite{

    private int range;
    private boolean deplacement;
    
    public Soldat(int cout, Joueur appartient, int income,int niveau, int range ){
        super(cout, appartient, income, niveau);
        
        this.range=range;
        this.deplacement=true;
    }

    public boolean move(){
        return false;
    }

    public void setdeplacement(boolean a){
        this.deplacement=a;
    }

    public boolean getdeplacement() {
		return deplacement;
	}
}
