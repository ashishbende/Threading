package deadlocks;

public class Account {
	private int balance = 1000;

	public void deposit(int amount) {
		balance += amount;
	}

	public void withdraw(int amount) {
		if (balance != 0) {
			balance -= amount;
		}
	}

	public int getBalance() {
		return balance;
	}

	public static void transfer(Account fromAccount, Account toAccount, int amount) {
		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}

}
