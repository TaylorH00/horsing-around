package GUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TrackPanel extends JPanel {
	private final ImageIcon imgTrack = new ImageIcon("src\\Resources\\track.jpg");
	
	private final int MAX_WIDTH = 250;
	private final int MAX_HEIGHT = 250;
	
	public static List<Horse> Horses = new ArrayList<Horse>();
	
	public TrackPanel() {
		generateAIHorses();
		
		Horses.add(App.player.getHorse());
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
	
	private void generateAIHorses() {
		int yCoord = -60;
		for(int i = 0; i<3; i++) {
			Horses.add(new Horse("AI #"+(i+1), yCoord));
			yCoord += 60;
		}
	}
}
