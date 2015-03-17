package Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeBefehl extends KeyAdapter{
	Snake snake;

	public SnakeBefehl(Snake snake){
		this.snake = snake;
	}

	public void keyPressed(KeyEvent e) {
		 
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			snake.bewegungLinks();
		break;
		case KeyEvent.VK_RIGHT:
			snake.bewegungRechts();
		break;
		case KeyEvent.VK_DOWN:
			snake.bewegunUnten();
		break;
		case KeyEvent.VK_UP:
			snake.bewegungHoch();
		break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		break;
		}
	}
	
}
