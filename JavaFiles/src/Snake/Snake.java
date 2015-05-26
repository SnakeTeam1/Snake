package Snake;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList; 

import javax.swing.text.Segment;



public class Snake {
	ArrayList<Block> bloecke;
	SpielSnake game;
	boolean rechts, links, oben, unten;
	long pause = 100;
	long zeitLetztePause = 0;
	Block kopf;
	boolean bewegt;
	
	public Snake(int xPos, int yPos, SpielSnake game) {
		this.game = game;
		bloecke = new ArrayList<Block>();
		bloecke.add(new Block(xPos,yPos));
		kopf = bloecke.get(0);
	}
	
	//Bewegungsrichtung der Schlage wird auf Hoch gesetzt 
	public void bewegungHoch(){
		if(!unten && !bewegt){
			bewegt = true;
			resetRichtung();
			oben = true;
		}
	}
	
	//Bewegungsrichtung der Schlage wird auf Unten gesetzt 
	public void bewegunUnten(){
		if(!oben && !bewegt){
			bewegt = true;
			resetRichtung();
			unten = true;
		}
	}
	
	//Bewegungsrichtung der Schlage wird auf Rechts gesetzt 
	public void bewegungRechts(){
		if(!links && !bewegt){
			bewegt = true;
			resetRichtung();
			rechts = true;
		}
	}
	
	//Bewegungsrichtung der Schlage wird auf Links gesetzt 
	public void bewegungLinks(){
		if(!rechts && !bewegt){
			bewegt = true;
			resetRichtung();
			links = true;
		}
	}
	
	//Es wird ein Block der Schlange hinzugefügt
	public void blockHinzufügen() {
		bloecke.add(new Block(bloecke.get(bloecke.size()-1).getOldX(),bloecke.get(bloecke.size()-1).getOldY()));
	}

	//Die Anfangsrichtung wird per Zufall bestimmt
	public void anfangsRichtung(){
		resetRichtung();
		if(Math.random()<0.5){
			rechts = true;
		} else{
			unten = true;
		}
	}

	//Die Richtung wird zurückgesetzt
	public void resetRichtung() {
		unten = false;
		oben = false;	
		rechts = false;	
		links = false;	
	}

	//Die Schlange wird geupdatet
	public void update() {
		if(System.currentTimeMillis() - zeitLetztePause > pause) {
			//Kopf bewegen
			bewegt = false;
			kopfBewegen();
			for(int i = 0;i < bloecke.size()-1;i++){
				bloecke.get(i+1).setPosition(bloecke.get(i).getOldX(), bloecke.get(i).getOldY());
			}
			game.spielfeldReset();
			for(Block i : bloecke){
				try {
					game.spielFeld[i.getX()][i.getY()]=true;
				}catch(ArrayIndexOutOfBoundsException e){
					
				}
			}
			zeitLetztePause = System.currentTimeMillis();
		}
	}

	//Der Kopf wird weiter bewegt
	private void kopfBewegen() {
		if(rechts){
			kopf.verschieben(1, 0);
		}
		if(links){
			kopf.verschieben(-1, 0);
		}
		if(oben){
			kopf.verschieben(0, -1);
		}
		if(unten){
			kopf.verschieben(0, 1);
		}
	}
	
	//return x Kordinate des Kopfes
	public int getKopfX() {
		return kopf.getX();
	}

	//return y Kordinate des Kopfes
	public int getKopfY() {
		return kopf.getY();
	}	
}
