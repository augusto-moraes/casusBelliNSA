
public class Cases{
	

		public int x;
		public int y;
		//Unite unite;
		public int color;
		public boolean taken;
	
	public Cases(int x,int y, int color,boolean taken) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.taken = taken;
	}
	


	public int getColor() {return color;}
	public void setColor(int color) {
		this.color = color;
		taken = true;
		} 

}

