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
	
	//Die Position des Blocks wird gesetzt
	public void setPosition(int x, int y){
		oldx = this.x;
		oldy = this.y;
		this.x = x;
		this.y = y;
	}
	
	//Der Block wird verschoben
	public void verschieben(int x, int y){
		oldx = this.x;
		oldy = this.y;
		this.x += x;
		this.y += y;
	}
	
	//return x Kordinate des Blocks
	public int getX() {
		return x;
	}

	//return y Kordinate des Blocks
	public int getY() {
		return y;
	}
	
	//return alte x Kordinate des Blocks
	public int getOldX() {
		return oldx;
	}
	
	//return alte y Kordinate des Blocks
	public int getOldY() {
		return oldy;
	}

}
