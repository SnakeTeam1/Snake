package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpielSnake extends JPanel implements Runnable {


	private static final long serialVersionUID = 1L;
	JFrame frame;
	int hoehe = 600;
	int breite = hoehe;
	int aufloesung = 20;
	int blockgroesse = (int)hoehe/aufloesung;
	boolean[][] spielFeld = new boolean[aufloesung][aufloesung];
	public Snake snake;
	int essenX;
	int essenY;
	Thread t;
	Event event;
	Color snakeFarbe = Color.black;
	BufferedImage hintergrund;
	BufferedImage schlange;
	BufferedImage schlangenkopf;
	BufferedImage essen;
	int kopfX;
	int kopfY;

	public static void main(String[] args) {
		new SpielSnake();
	}

	public SpielSnake() {
        this.setPreferredSize(new Dimension(hoehe,breite));
        event = new Event(this);
		frame = new JFrame();
		setzteTitel();
        frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600,200);
        frame.add(this);
        frame.pack();
		frame.setResizable(false);
		event.popup();
	}
	//Spiel wird inizialisiert
	public void initialize(int tempo) {
		spielfeldReset();
		snake = new Snake(0,0,this);
		snake.anfangsRichtung();
		snake.blockHinzufügen();
		essenPlatzieren();
		t = new Thread(this);
		t.start();
		snake.pause = 300/tempo;
		frame.addKeyListener(new SnakeBefehl(snake));
		try {
			hintergrund = ImageIO.read(new File("./img/hintergrund.jpg"));
			schlange = ImageIO.read(new File("./img/schlange.jpg"));
			schlangenkopf = ImageIO.read(new File("./img/schlangenkopf.png"));
			essen = ImageIO.read(new File("./img/essen.jpg"));
		} catch(IOException ex) {
			//nichts tun
		}
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(hintergrund, 0, 0,hoehe+20,breite+20, null);
		for(int x = 0 ; x < aufloesung ; x++){
			for(int y = 0 ; y < aufloesung ; y++){
					if(spielFeld[x][y]){
						g.drawImage(schlange, x*blockgroesse, y*blockgroesse,blockgroesse,blockgroesse, null);
					}
			}		
		}
		g.drawImage(schlangenkopf, kopfX*blockgroesse, kopfY*blockgroesse,blockgroesse,blockgroesse, null);	
		g.drawImage(essen, essenX*blockgroesse, essenY*blockgroesse,blockgroesse,blockgroesse, null);
		setzteTitel();
	}
	
	//Essen wird auf dem Spielfeld platziert
	public void essenPlatzieren() {
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		for(int i = 0; i < aufloesung; i++){
			for(int j = 0; j < aufloesung; j++){
				if(!spielFeld[i][j]){
					x.add(i);
					y.add(j);
				}
			}
		}
		int zufall = (int) (Math.random() * (x.size() - 1));
		essenX = x.get(zufall);
		essenY = y.get(zufall);
	}

	@Override
	public void run() {
		while (!t.isInterrupted()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			snake.update();
			event.ueberpruefen();
			kopfX = snake.getKopfX();
			kopfY = snake.getKopfY();
			repaint();
		}
		event.popup();
	}
	
	//Das Array spielFeld wir auf false gesetzt
	public void spielfeldReset(){
		for(int x = 0; x < aufloesung; x++){
			for(int y = 0; y < aufloesung; y++){
				spielFeld[x][y] = false;				
			}
		}
	}
	
	//Der Titel wird Aktualisiert
	 public void setzteTitel(){
			frame.setTitle("Snake von Damian, Michael und Roger! Punktzahl: "+ event.punktzahl + " Highscore: "+ event.highscore);
	 }
}
