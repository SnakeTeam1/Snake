package Snake;

import javax.swing.JOptionPane;

public class Event {
	SpielSnake game;
	
	public Event(SpielSnake game){
		this.game = game;
	}
	
	public void ueberpruefen(){
		kanibale();
		essen();
		crashMitWand();		
	}

	private void crashMitWand() {
		if(game.snake.getKopfX() < 0){
			gameOver();
		}
		if(game.snake.getKopfX() > game.aufloesung-1){
			gameOver();
		}
		if(game.snake.getKopfY() < 0){
			gameOver();
		}
		if(game.snake.getKopfY() > game.aufloesung-1){
			gameOver();
		}
	}

	public void gameOver() {
		game.t.interrupt();
	}

	public void kanibale() {
		for(int i = 1; i < game.snake.bloecke.size() - 1; i++){
			if(game.snake.bloecke.get(i).getX() == game.snake.getKopfX() && 
					game.snake.bloecke.get(i).getY() == game.snake.getKopfY()){
				gameOver();
			}
		}
		
	}

	public void essen() {
		if(game.snake.getKopfX() == game.essenX && game.snake.getKopfY() == game.essenY){
			game.snake.blockHinzufügen();
			game.essenPlatzieren();
		}
	}

	public void popup() {
		new Thread() {
			public void run() {
				int n = JOptionPane.showConfirmDialog(game.frame, "New Game?",
						"Question", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.OK_OPTION) {
					game.initialize();
				} else {
					System.exit(0);
				}
			}
		}.start();
	}
}
