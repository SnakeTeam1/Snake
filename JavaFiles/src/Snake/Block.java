package Snake;

public class Block {
	private int x;
	private int y;
	private int oldx;
	private int oldy;
	
	public Block(int x, int y){
		this.x = x;
		this.y = x;
		this.oldx = x;
		this.oldy = x;
	}
	
	public void setPosition(int x, int y){
		oldx = this.x;
		oldy = this.y;
		this.x = x;
		this.y = y;
	}
	
	public void verschieben(int x, int y){
		oldx = this.x;
		oldy = this.y;
		this.x += x;
		this.y += y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getOldX() {
		return oldx;
	}
		 
	public int getOldY() {
		return oldy;
	}

}
