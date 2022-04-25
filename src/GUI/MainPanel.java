package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Resources.Styles;

public class MainPanel extends JPanel implements ActionListener {
	public static final TrackPanel trackPanel = new TrackPanel();
	public static Horse winner;
	private int raceCount;
	
	private JButton btnRace;
	private JButton btnShop;
	private JButton btnBet;
	private JTextField txtBettingAmount;
	public static JLabel lblBalance;
	public static JTextArea txtCommentator;
	
	public MainPanel() {
		this.setMinimumSize(new Dimension(800,600));
		this.setLayout(new BorderLayout());
		
		this.add(trackPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new GridLayout(1,2));
		
		txtCommentator = new JTextArea(10, 25);
		txtCommentator.setLineWrap(true);
		txtCommentator.setWrapStyleWord(true);
		txtCommentator.setText("Welcome to the track! Click RACE to watch Dunker compete against other horses! You can win money from racing and betting, so throw a couple dollars into the pot!\n");
		txtCommentator.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtCommentator); 
		controlPanel.add(scrollPane);
		
		
		//Create and configure btnPanel
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,3));
		
		btnRace = Styles.StyledJButton("RACE");
		btnRace.addActionListener(this);
		btnPanel.add(btnRace);
		btnShop = Styles.StyledJButton("SHOP");
		btnShop.addActionListener(this);
		btnPanel.add(btnShop);
		
		//Create and configure bettingPanel
		JPanel bettingPanel = new JPanel();
		bettingPanel.setLayout(new GridLayout(1,2));
		
		txtBettingAmount = new JTextField();
		txtBettingAmount.setToolTipText("Enter a whole integer amount");
		bettingPanel.add(txtBettingAmount);
		btnBet = Styles.StyledJButton("BET");
		btnBet.addActionListener(this);
		bettingPanel.add(btnBet);
		
		//Create and configure balancePanel
		JPanel balancePanel = new JPanel();
		balancePanel.setLayout(new GridLayout(3,1));
		
		JLabel lblBalanceDesc = new JLabel("BALANCE:");
		lblBalance = new JLabel("$" + String.valueOf(App.player.getBalance()));
		
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
			beginRace();
			raceCount++;
			txtCommentator.setText(txtCommentator.getText() + "Race #"+raceCount+" has begun!\n");
		}
		else if(e.getSource() == btnShop) {
			ShopDialog shop = new ShopDialog();
			shop.setFocusableWindowState(isFocusable());
		}
		else if(e.getSource() == btnBet) {
			int bet = Integer.parseInt(txtBettingAmount.getText());
			txtBettingAmount.setText("");
			if(!App.player.decreaseBalance(bet)) {
				JOptionPane.showMessageDialog(this, "You don't have enough funds this bet.","Insufficient Funds", JOptionPane.ERROR_MESSAGE);
				return;
			}
			App.player.setBet(bet);
			txtCommentator.setText(txtCommentator.getText() + "$" + bet + " bet has been placed!\n");
		}
		
	}
	
	public void beginRace() {
		this.winner = null;
		for(GUI.Horse horse : trackPanel.Horses) {
			Thread thread = new Thread(horse);
			thread.start();
		}
		App.frame.setEnabled(false);
	}
	
	//Outputs first winner to screen and updates player balance if they won
	public static void declareWinner(Horse horse) {
		if(winner == null) {
			winner = horse;
            txtCommentator.setText(MainPanel.txtCommentator.getText() + winner.getName() + " has won the race!\n");
            if(winner == App.player.getHorse()) {
            	//Connect to bet
            	txtCommentator.setText(MainPanel.txtCommentator.getText() + "Congratulations! You have won $100 plus $"+App.player.getPot()+" from the pot\n");
            	App.player.increaseBalance(App.player.getPot() + 100);
            }
            App.player.setBet(0);
            App.frame.setEnabled(true);
		}
	}
}
