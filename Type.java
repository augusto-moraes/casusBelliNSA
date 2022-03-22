
public class Type{
	

		public int x;
		public int y;
		public int radius;
		public int colour;
	
	public Type(int x,int y, int r) {
		this.x = x;
		this.y = y;
		this.radius = r;
	}
	
	public double getInscrit() {
		System.out.println(Math.cos(Math.PI/6));
		return (radius*Math.cos(Math.PI/6));
		
	}

	
	public boolean inside(int p, int j) {
		if (Math.pow(x-p,2)  + Math.pow(y-j,2)  <= Math.pow(radius*Math.cos(Math.PI/6),2)) {
			return true;
		} else {return false;}
	} 
}

