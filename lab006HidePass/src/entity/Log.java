package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Log implements Serializable {
	private String accountId;
	private Date date;
	private String function;
	private int month;
//	Date d = new Date();
	private Calendar cal;

	public Log(String accountId) {
		this.accountId = accountId;
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
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void print() {
		System.out.println("|" + date + "|" + accountId + "|" + function);
	}
}
