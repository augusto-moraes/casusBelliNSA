public class Batiment extends Unite {
  private String type;
    
  public Batiment(int cout, Joueur appartient, int income,int niveau, String type){
    super(cout, appartient,income, niveau);
    this.type=type;
  }
}
