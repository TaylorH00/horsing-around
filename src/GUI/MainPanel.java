package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Resources.Styles;

public class MainPanel extends JPanel implements ActionListener {
	public static final TrackPanel trackPanel = new TrackPanel();
	private JButton btnRace;
	private JButton btnShop;
	private JButton btnBet;
	private JTextField txtBettingAmount;
	private JLabel lblBalance;
	private int raceCount;
	public static JTextArea txtCommentator;
	public MainPanel() {
		this.setMinimumSize(new Dimension(800,600));
		this.setLayout(new BorderLayout());
		
		this.add(trackPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new GridLayout(1,2));
		
		txtCommentator = new JTextArea(10, 25);
		txtCommentator.setText("Welcome to the track! Click race to run your horse!\n");
		JScrollPane scrollPane = new JScrollPane(txtCommentator); 
		txtCommentator.setEditable(false);
		controlPanel.add(scrollPane);
		
		
		//Create and configure btnPanel
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,3));
		
		btnRace = Styles.StyledJButton("RACE");
		btnPanel.add(btnRace);
		btnShop = Styles.StyledJButton("SHOP");
		btnPanel.add(btnShop);
		
		//Create and configure bettingPanel
		JPanel bettingPanel = new JPanel();
		bettingPanel.setLayout(new GridLayout(1,2));
		
		txtBettingAmount = new JTextField();
		txtBettingAmount.setToolTipText("Enter a whole integer amount");
		bettingPanel.add(txtBettingAmount);
		btnBet = Styles.StyledJButton("BET");
		bettingPanel.add(btnBet);
		
		//Create and configure balancePanel
		JPanel balancePanel = new JPanel();
		balancePanel.setLayout(new GridLayout(3,1));
		
		JLabel lblBalanceDesc = new JLabel("BALANCE:");
		lblBalance = new JLabel("$" + String.valueOf(App.playerHorse.getBalance()));
		
		balancePanel.add(bettingPanel);
		balancePanel.add(lblBalanceDesc);
		balancePanel.add(lblBalance);
		
		btnPanel.add(balancePanel);
		controlPanel.add(btnPanel);
		this.add(controlPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRace) {
			trackPanel.beginRace();
			raceCount++;
			txtCommentator.setText(txtCommentator.getText() + "Race #"+raceCount+" has begun!\n");
		}
		else if(e.getSource() == btnShop) {
			ShopDialog shop = new ShopDialog();
			shop.setFocusableWindowState(isFocusable());
		}
		else if(e.getSource() == btnBet) {
			int bet = Integer.parseInt(txtBettingAmount.getText());
			if(bet > App.playerHorse.getBalance()) {
				JOptionPane.showMessageDialog(this, "You don't have enough funds this bet.","Insufficient Funds", JOptionPane.ERROR_MESSAGE);
			}
			else {
				App.playerHorse.decreaseBalance(bet);
				lblBalance.setText("$" +String.valueOf(App.playerHorse.getBalance()));
				txtCommentator.setText(txtCommentator.getText() + "$" + bet + " bet has been placed!\n");
			}
		}
		
	}
}
