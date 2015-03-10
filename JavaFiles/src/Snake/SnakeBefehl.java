package Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeBefehl {
	Snake snake;


	public void keyPressed(KeyEvent e) {
		 
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		Snake.moveLeft();
		break;
		case KeyEvent.VK_RIGHT:
		Snake.moveRight();
		break;
		case KeyEvent.VK_DOWN:
		Snake.moveDown();
		break;
		case KeyEvent.VK_UP:
		Snake.moveUp();
		break;
		
		case KeyEvent.VK_ESCAPE:
		System.exit(0);
		break;
		}
	}
	
}
