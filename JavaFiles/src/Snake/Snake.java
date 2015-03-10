package Snake;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList; 


public class Snake {
	
	private static int x;
	private static int y;
	private int oldx;
	private int oldy;
	private int Geschwindigkeit = 1;
	
	
	public Snake(int xPos, int yPos, Color f) {
		
		x = xPos;
		y = yPos;
		snakeErzeugen();
	}
	
	public void snakeErzeugen(){
		
		
	}
	
	public static void moveUp(){
		
	}
	
	public static void moveDown(){
		
	}
	
	public static void moveRight(){
		
	}
	
	public static void moveLeft(){
		
	}
	
	public void addSegment() {
		
	}
	
    public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e){}
			// ignoring exception at the moment
	}

	public void translate(int x, int y) {
		oldx = this.x;
		oldy = this.y;
		this.x += x;
		this.y += y;
		}
	
	public void setPosition(int x, int y) {
		oldx = this.x;
		oldy = this.y;
		this.x = x;
		this.y = y;
		}
	
	public static int getXPosition() {
		return x;
	}

	public static int getYPosition() {
		return y;
	}
	
	public int getOldX() {
		return oldx;
	}
		 
	public int getOldY() {
		return oldy;
	}
}
