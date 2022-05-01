public class Batiment extends Unite {
  private String type;
    
  public Batiment(int cout, Joueur appartient, int income,int niveau, String type){
    super(cout, appartient,niveau, income);
    this.type=type;
  }
}
