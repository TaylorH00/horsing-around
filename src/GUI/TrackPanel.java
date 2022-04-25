package GUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Horses.*;

public class TrackPanel extends JPanel {
	private final ImageIcon imgTrack = new ImageIcon("src\\Resources\\track.jpg");
	
	public static final int MAX_Y = 800;
	private final int MAX_WIDTH = 250;
	private final int MAX_HEIGHT = 250;
	
	private List<Horse> Horses = new ArrayList<Horse>();
	
	public TrackPanel() {
		generateAIHorses();
		
		Horses.add(App.playerHorse);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Paint the background
		g.drawImage(imgTrack.getImage(), 0, 0, 800, 400, this);
		
		Graphics2D g2d = (Graphics2D) g;
		//Paint the horses
		for(Horse horse : Horses) {
			g2d.drawImage(horse.getSprite(), horse.getX_COORD(), horse.getY_COORD(), MAX_WIDTH, MAX_HEIGHT, this);
		}
	}
	
	public void beginRace() {
		//Starts the threads
		for(Horse horse : Horses) {
			Thread thread = new Thread(horse);
			thread.start();
		}
	}
	
	private void generateAIHorses() {
		Random ran = new Random();
		int yCoord = -60;
		for(int i = 0; i<3; i++) {
			int value = ran.nextInt(1,3);
			if(value == 1) {
				Horses.add(new Thoroughbred("AI"+(i+1), yCoord));
			}
			else if(value == 2) {
				Horses.add(new Appaloosa("AI"+(i+1), yCoord));
			}
			else if(value == 3) {
				Horses.add(new Standardbred("AI"+(i+1), yCoord));
			}
			yCoord += 60;
		}
	}
}
