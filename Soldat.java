public class Soldat extends Unite{
  private int niveau;
  private int range;

  public Soldat(int cout, Joueur appartient, int income,int niveau, int range ){
    super(cout, appartient, income);
    this.niveau=niveau;
    this.range=range;

  }

  public boolean move(){


    return false;

  }





}
