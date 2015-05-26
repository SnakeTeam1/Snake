package Snake;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
			hintergrund = ImageIO.read(new File("./back.jpg"));
			schlange = ImageIO.read(new File("./snake.jpg"));
			schlangenkopf = ImageIO.read(new File("./schlangenkopf.png"));
			essen = ImageIO.read(new File("./essen.png"));
		} catch(IOException ex) {
			//nichts tun
		}
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(hintergrund, 0, 0,hoehe+10,breite+10, null);
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
