package Snake;

import java.awt.Dimension;

import javax.swing.JOptionPane;

public class Event {
	SpielSnake game;
	int highscore = 0;
	int punktzahl = 0;
	int spieltempo = 0;
	
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
		if (highscore < punktzahl){
			highscore = punktzahl;
		}
		punktzahl = 0;
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
			punktzahl = punktzahl + spieltempo;
		}
	}

	public void popup() {
		new Thread() {
			public void run() {
				try{
					Object[] options = {"Langsam", "Normal", "Schnell", "Beenden"};
					spieltempo = JOptionPane.showOptionDialog(game.frame,"Wie schnell wollen sie spielen?",
							"Highscore: " + highscore , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,	options, options[1]) + 1;
					if (spieltempo != 4) {
						punktzahl = 0;
						game.initialize(spieltempo);
					} else {
						System.exit(0);
					}
				}
				catch (ArithmeticException e){
					System.exit(0);
				}
			}
		}.start();
	}
}
