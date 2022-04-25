package GUI;

public class PlayerCharacter {
	private Horse horse;
	private int bet;
	private int pot;

	public int balance = 1000;
	
	public Horse getHorse() {
		return horse;
	}
	
	public int getBalance() {
		return balance;
	}

	public void increaseBalance(int amount) {
		this.balance += amount;
		MainPanel.lblBalance.setText("$" +String.valueOf(balance));
	}
	
	public Boolean decreaseBalance(int amount) {
		if(amount > balance) {
			return false;
		}
		this.balance -= amount;
		MainPanel.lblBalance.setText("$" +String.valueOf(balance));
		return true;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
		this.pot = bet * 3;
	}
	
	public int getPot() {
		return pot;
	}

	public PlayerCharacter(Horse horse) {
		this.horse = horse;
	}
	
}
