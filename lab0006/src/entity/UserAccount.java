package entity;

import java.io.Serializable;

public class UserAccount implements Serializable{
	private String accountId;
	private String accountName;
	private String password;
	private double money;
	
	public UserAccount() {}
	
	public UserAccount(String accountName) {
		this.accountName = accountName;
		this.money = 0;
	}
	
	public UserAccount(String accountId, String accountName, String password) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.money = 0;
	}
	
	public UserAccount(String accountId, String accountName, String password, double money) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.money = money;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public void print() {
		System.out.format("|%-20s|%-20s|%-20s|%-20.5f|\n", accountId, accountName, password, money);
	}
}
