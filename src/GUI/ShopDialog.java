package GUI;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;

import Resources.Styles;

public class ShopDialog extends JDialog implements ActionListener {
	private JSlider sldSpeed;
	private JButton btnBuySpeed;
	
	public ShopDialog() {
		this.setTitle("Welcome to Dunker's Deals!");
		this.setModal(true);
		this.setSize(500,300);
		this.setLayout(new GridLayout(5, 2));
		
		JLabel lblUpgrade = new JLabel("Upgrades");
		JLabel lblUpgradeFiller = new JLabel("-------------------------");
		this.add(lblUpgrade);
		this.add(lblUpgradeFiller);
		
		this.add(createSpeedSlider());
		this.add(createSpeedButton());
		
		//Create and configure lblOutfits and lblOutfitsFiller
		JLabel lblOutfits = new JLabel("Outfits");
		JLabel lblOutfitsFiller = new JLabel("-------------------------");
		this.add(lblOutfits);
		this.add(lblOutfitsFiller);

		this.add(createSolarOutfitButton());
		this.add(createRedOutfitButton());
		this.add(createDigitalOutfitButton());
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = ((JButton) e.getSource()).getActionCommand();
		
		if(actionCommand == "increaseSpeed") {
			App.playerHorse.increaseSpeed(1);
			this.sldSpeed.setValue(App.playerHorse.getSpeed());
			if(App.playerHorse.getSpeed() == 10) {
				this.btnBuySpeed.setEnabled(false);
			}
			return;
		}
		else {
			App.playerHorse.setSprite(new ImageIcon("src\\Resources\\"+actionCommand+".png").getImage());
		}
		MainPanel.trackPanel.repaint();
	}
	
	private JButton createSolarOutfitButton() {
		JButton btnSolarHorse = Styles.StyledJButton("$100");
		
		btnSolarHorse.setIcon(new ImageIcon(new ImageIcon("src\\Resources\\solarhorse.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		btnSolarHorse.setActionCommand("solarhorse");
		btnSolarHorse.addActionListener(this);
		return btnSolarHorse;
	}
	
	private JButton createRedOutfitButton() {
		JButton btnRedHorse = Styles.StyledJButton("$50");
		
		btnRedHorse.setIcon(new ImageIcon(new ImageIcon("src\\Resources\\redhorse.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		btnRedHorse.setActionCommand("redhorse");
		btnRedHorse.addActionListener(this);
		return btnRedHorse;
	}
	
	private JButton createDigitalOutfitButton() {
		JButton btnDigitalHorse = Styles.StyledJButton("$300");
		
		btnDigitalHorse.setIcon(new ImageIcon(new ImageIcon("src\\Resources\\digitalhorse.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		btnDigitalHorse.setActionCommand("digitalhorse");
		btnDigitalHorse.addActionListener(this);
		return btnDigitalHorse;
	}
	
	private JButton createSpeedButton() {
		btnBuySpeed = Styles.StyledJButton("BUY SPEED $50");
		btnBuySpeed.setActionCommand("increaseSpeed");
		btnBuySpeed.addActionListener(this);
		return btnBuySpeed;
	}
	
	private JSlider createSpeedSlider() {
		sldSpeed = new JSlider(1,10,App.playerHorse.getSpeed());
		this.sldSpeed.setEnabled(false);
		sldSpeed.setPaintTicks(true);
		sldSpeed.setPaintLabels(true);
		sldSpeed.setPaintTrack(true);
		sldSpeed.setMajorTickSpacing(1);
		return sldSpeed;
	}
}
