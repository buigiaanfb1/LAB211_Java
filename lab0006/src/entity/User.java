package entity;

import java.io.Serializable;

public class User implements Serializable{
	private String accountName;
	private String password;
	
	public User() {}
	
	public User(String accountName) {
		this.accountName = accountName;
	}
	
	public User(String accountName, String password) {
		this.accountName = accountName;
		this.password = password;
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

	public void print() {
		System.out.format("|%-20s|%-20s\n", accountName, password);
	}
}