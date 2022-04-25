package GUI;

import java.awt.Image;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import GUI.MainPanel;

public class Horse extends JComponent implements Runnable {
	private int speed = 1;
	
	private String name; 
	private Image sprite = new ImageIcon("src\\Resources\\oldhorse.png").getImage(); //Default AI sprite
	
	private int X_COORD = -100; 
	private final int Y_COORD;
	
	public int getSpeed() {
		return speed;
	}

	public void incrementSpeed() {
		this.speed++;
	}
	
	public String getName() {
		return name;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void setSprite(Image image) {
		this.sprite = image;
	}

	public int getX_COORD() {
		return X_COORD;
	}

	public void setX_COORD(int x_COORD) {
		X_COORD = x_COORD;
	}
	
	public int getY_COORD() {
		return Y_COORD;
	}
	
	public Horse(String name, int yCoord) {
		this.name = name;
		this.Y_COORD = yCoord;
	}
	
	//Moves horse along screen until it reaches the finish line (max-screen width)
	// and updates MainPanel with the first winner.
	@Override
	public void run() {
		Random ran = new Random();
		Lock repaintLock = new ReentrantLock();
		Boolean finished = false;
		
		while (finished == false)
	    {
			this.X_COORD += ran.nextInt(1,10) + this.speed;
			
			repaintLock.lock();
			try {
				if(this.X_COORD >= 800)
		        {
		        	finished = true;
		            MainPanel.declareWinner(this);
		            this.X_COORD = -100;
		        }
				MainPanel.trackPanel.repaint();
			}
			finally {
				repaintLock.unlock();	
			}
			
	        try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}
}
