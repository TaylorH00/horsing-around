package GUI;

import Horses.Horse;

public class PlayerCharacter {
	public Horse horse;
	public int balance = 0;
	public String name;
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public PlayerCharacter(String name, Horse horse) {
		this.name = name;
		this.horse = horse;
	}
	
}
