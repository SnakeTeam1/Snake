package Snake;

import javax.swing.JOptionPane;

public class Event {
	SpielSnake game;
	int highscore = 0;
	int punktzahl = 0;
	int spieltempo = 0;
	boolean wand;
	
	public Event(SpielSnake game){
		this.game = game;
	}
	
	//Es wird �berpr�ft ob ein Event eintrifft
	public void ueberpruefen(){
		kanibale();
		essen();
		crashMitWand();		
	}
	
	//Es wird �berpr�ft ob der Kopf in die Wand kracht mit Unterscheidung je nach Entscheidung bei Spielbeginn
	private void crashMitWand() {
		if(wand){
			if(game.snake.getKopfX() < 0){
				game.snake.kopf.setPosition(game.aufloesung - 1, game.snake.kopf.getY());
			}
			if(game.snake.getKopfX() > game.aufloesung-1){
				game.snake.kopf.setPosition(0, game.snake.kopf.getY());
			}
			if(game.snake.getKopfY() < 0){
				game.snake.kopf.setPosition(game.snake.kopf.getX() , game.aufloesung - 1);
			}
			if(game.snake.getKopfY() > game.aufloesung-1){
				game.snake.kopf.setPosition(game.snake.kopf.getX() , 0);
			}
		}else{
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
	}

	//Es wird �berpr�ft ob die Schlange sich selbst frisst
	public void kanibale() {
		for(int i = 1; i < game.snake.bloecke.size() - 1; i++){
			if(game.snake.bloecke.get(i).getX() == game.snake.getKopfX() && 
					game.snake.bloecke.get(i).getY() == game.snake.getKopfY()){
				gameOver();
			}
		}
		
	}

	//Es wird �berpr�ft ob die Schlange das Futter frisst
	public void essen() {
		if(game.snake.getKopfX() == game.essenX && game.snake.getKopfY() == game.essenY){
			game.snake.blockHinzuf�gen();
			game.essenPlatzieren();
			punktzahl = punktzahl + spieltempo;
		}
	}
	
	 //Spiel ende und beginn neues Spiel
	public void gameOver() {
		if (highscore < punktzahl){
			highscore = punktzahl;
		}
		punktzahl = 0;
		game.t.interrupt();
	}
	
	//Popup Fenster f�r den Spielbeginn
	public void popup() {
		new Thread() {
			public void run() {
				try{
					Object[] options = {"Ja", "Nein"};
					int n = JOptionPane.showOptionDialog(game.frame,"Wollen Sie durch W�nde gehen k�nnen?",
							"Highscore: " + highscore , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,	options, options[0]) ;
					if (n == 0) {
						wand = true;
					} else if (n == 1) {
						wand = false;
					} else {
						System.exit(0);
					}
				}
				catch (ArithmeticException e){
					System.exit(0);
				}
				try{
					Object[] options = {"Langsam", "Normal", "Schnell", "Beenden"};
					spieltempo = JOptionPane.showOptionDialog(game.frame,"Wie schnell wollen Sie spielen?",
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
