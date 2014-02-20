public class Bird extends Images{
	private final double gravity = 0.2;
	private double ychange;
	public Bird(){
		super("flapping2.png", 150, 200);
		
		ychange = 0.15;
	}
	
	public double getYchange() {
		return ychange;
	}

	public void setYchange(double ychange) {
		this.ychange = ychange;
	}
	
	public void addGravity(){
		ychange += gravity;
	}
	
	
}

//super("tube.png", 16, 81);
