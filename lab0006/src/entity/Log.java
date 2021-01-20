package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Log implements Serializable{
	private String accountId;
	private Date date;
	private String function;
//	Date d = new Date();

	public Log(String accountId) {
		this.accountId = accountId;
		this.date = new Date();
	}

	public Log() {
	}

	public Log(String accountId, String function) {
		this.accountId = accountId;
		this.date = new Date();
		this.function = function;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void print() {
		System.out.println( "|" + date + "|" + accountId + "|" + function);
	}
}
