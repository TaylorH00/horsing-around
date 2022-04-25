package Horses;
import java.awt.Image;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import GUI.MainPanel;

public class Horse extends JComponent implements Runnable {
	private int balance = 10;
	private int speed = 1;
	
	private String name; 
	private Image sprite = new ImageIcon("src\\Resources\\oldhorse.png").getImage(); //Default AI sprite
	
	private int X_COORD = -100; 
	private final int Y_COORD;
	
	public int getBalance() {
		return balance;
	}

	public void increaseBalance(int amount) {
		this.balance += amount;
	}
	
	public void decreaseBalance(int amount) {
		this.balance -= amount;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void increaseSpeed(int delta) {
		this.speed += delta;
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
				if(this.X_COORD >= MainPanel.trackPanel.MAX_Y)
		        {
		        	finished = true;
		            MainPanel.txtCommentator.setText(MainPanel.txtCommentator.getText() + this.name + " is the winner of the race!\n");
		            this.X_COORD = -100;
		            break;
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
