package GUI;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Horses.Horse;
import Horses.Thoroughbred;

public class App {
	public static JFrame frame;
	public static Horse playerHorse;
	public static void main(String[] args) {
		frame = new JFrame("Horsing Around");  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(new Dimension(800,600));
		frame.setResizable(false);
		
		playerHorse = new Thoroughbred("Bill", 100);
		playerHorse.setSprite(new ImageIcon("src\\Resources\\defaulthorse.png").getImage());
		frame.add(new StartPanel());  
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);  
	}

}
