package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Resources.Styles;

public class StartPanel extends JPanel implements ActionListener {
	private final Image background = new ImageIcon("src\\Resources\\background.jpg").getImage();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public StartPanel() {
		this.setLayout(new GridBagLayout());
		gbc.fill = gbc.HORIZONTAL;
		
		this.add(createTitleLabel(), gbc);
		
		this.add(createStartButton(), gbc);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		App.frame.getContentPane().removeAll();
		App.frame.getContentPane().add(new MainPanel());
		App.frame.revalidate();
	}
	
	private JLabel createTitleLabel() {
		gbc.ipady = 40;
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,150,0);
		
		JLabel lblTitle = new JLabel("Horsing Around");
		lblTitle.setBorder(Styles.border);
		lblTitle.setOpaque(true);
		lblTitle.setBackground(Styles.backgroundColor);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 34));
		lblTitle.setForeground(Styles.textColor);
		return lblTitle;
	}
	
	private JButton createStartButton() {
		gbc.ipady = 10;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(50,0,0,0);
		
		JButton btnStart = Styles.StyledJButton("START");
		btnStart.addActionListener(this);
		return btnStart;
	}
}
