package GUI;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App {
	public static JFrame frame;
	public static PlayerCharacter player;
	public static void main(String[] args) {
		frame = new JFrame("Horsing Around");  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(new Dimension(800,600));
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(App.class.getResource("/Resources/MSUIcon.png")).getImage());
		
		String horseName = "Dunker";
		Horse horse = new Horse(horseName, 110);
		horse.setSprite(new ImageIcon(App.class.getResource("/Resources/defaulthorse.png")).getImage());
		player = new PlayerCharacter(horse);
		
		frame.add(new StartPanel());  
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);  
	}
}
