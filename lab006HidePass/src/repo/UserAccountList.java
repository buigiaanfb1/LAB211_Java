package repo;

import entity.Log;
import entity.User;
import entity.UserAccount;
import helper.PasswordField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class UserAccountList extends ArrayList<UserAccount> {
	public static boolean successfullyLoggedIn = false;
	public static String accountId;
	public static String password;
	public static String function;

	Scanner sc = new Scanner(System.in);
	ArrayList<User> userList = new ArrayList<>();
	ArrayList<Log> logList = new ArrayList<>();
//    String password = pass.readPassword("Enter password: ");
//    System.out.println("The password entered is: "+password);

	/* Initialize FILE */
	String fBank = "bank.dat";
	String fUser = "user.dat";
	String fLog = "log.dat";

	/* Initialize encode, decode with Base64 */
	Base64.Encoder enc = Base64.getEncoder();
	Base64.Decoder dec = Base64.getDecoder();
//	String decoded = new String(dec.decode(o.getPassword()));
//	String encodePassword = enc.encodeToString(rePassword.getBytes());

	/* Load data when system online */
	public void loadData() {
		importFile(fBank);
		importFile(fUser);
		importFile(fLog);
		System.out.println("All data of customers are loaded!\n");
	}

	/* Find account name */
	private int findAccountName(String accountName) {
		for (int i = 0; i < this.size(); i++)
			if (this.get(i).getAccountName().equalsIgnoreCase(accountName))
				return i;
		return -1;
	}

	/* Find account id */
	private int findId(String aCode) {
		for (int i = 0; i < this.size(); i++)
			if (this.get(i).getAccountId().equalsIgnoreCase(aCode))
				return i;
		return -1;
	}

	/* Find log */
	private int findLog(String accountId) {
		for (int i = 0; i < logList.size(); i++)
			if (logList.get(i).getAccountId().equalsIgnoreCase(accountId))
				return i;
		return -1;
	}

	/* Print for only 1 user while logging */
	public void print() {
		for (UserAccount o : this) {
			if (o.getAccountId().equals(accountId))
				o.print();
		}
	}

	/* Print all 2 files for administrator */
	public void admin() {
		for (UserAccount o : this) {
			o.print();
		}
		for (User o : userList)
			o.print();
	}

	/* Import file */
	public void importFile(String fName) {
		try {
			File f = new File(fName);
			if (!f.exists()) {
				System.out.println("Empty list.");
				return;
			}
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream fo = new ObjectInputStream(fi);
			UserAccount userAccount;
			User user;
			Log log;
			if (fName.equalsIgnoreCase(fBank)) {
				while ((userAccount = (UserAccount) (fo.readObject())) != null) {
					this.add(userAccount);
				}
			}
			if (fName.equalsIgnoreCase(fUser)) {
				while ((user = (User) (fo.readObject())) != null) {
					userList.add(user);
				}
			} else
				while ((log = (Log) (fo.readObject())) != null) {
					logList.add(log);
				}
			fi.close();
			fo.close();
		} catch (Exception e) {
		}
	}

	public void saveToFile(String fName) {
		if (this.size() == 0 && userList.size() == 0)
			return;
		try {
			FileOutputStream f = new FileOutputStream(fName);
			ObjectOutputStream fo = new ObjectOutputStream(f);

			if (fName.equalsIgnoreCase(fBank))
				for (UserAccount o : this)
					fo.writeObject(o);
			else if (fName.equalsIgnoreCase(fUser))
				for (User o : userList)
					fo.writeObject(o);
			else
				for (Log o : logList)
					fo.writeObject(o);
			f.close();
			fo.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void createNewAccount() {
		successfullyLoggedIn = false;
		String accountId;
		String accountName;
		String password;
		int pos;
		boolean valid = true;
		String regex = "^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?\\d)"
				+ "(?=.*?[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?]).*$";
		do {
			System.out.print("Enter id of your account (VCBxxxxxx (x is digit)): ");
			accountId = sc.nextLine();
			pos = findId(accountId);
			valid = accountId.matches("^VCB\\d{6}$");
			if (pos >= 0)
				System.out.println("This id is duplicated.");
			else if (accountId.contains(" "))
				System.out.println("ID can't not contain blank.");
			else if (!valid)
				System.out.println("ID must match (VCBxxxxxx (x is digit))! Try again.");
		} while (pos >= 0 || accountId.contains(" ") || !valid);

		do {
			System.out.print("Enter account name: ");
			accountName = sc.nextLine();
			pos = findAccountName(accountName);
			if (pos >= 0)
				System.out.println("This account name is duplicated.");
			else if (accountName.contains(" "))
				System.out.println("Your account name can't not contain blank.");

		} while (pos >= 0 || accountId.contains(" ") || !valid);

		do {
			System.out.print("Enter your password: ");
//		    String password = pass.readPassword("Enter password: ");
//		    System.out.println("The password entered is: "+password);
			password = sc.nextLine();
			if (password.contains(" "))
				System.out.println("Your password can't not contain blank!");
			else if (password.length() < 5)
				System.out.println("You password must at least 6 letters.");
			else if (!password.matches(regex))
				System.out.println("Your password must include uppercase letters, "
						+ "lower case letters, numeric characters and 1 special character");
		} while (pos >= 0 || accountId.contains(" ") || password.length() < 5 || !password.matches(regex));

		String rePassword;
		while (true) {
			System.out.print("Enter password again: ");
			rePassword = sc.nextLine();
			if (rePassword.equalsIgnoreCase(password))
				break;
			else
				System.out.println("Password does not match! Please try again.");
		}
		String encodePassword = enc.encodeToString(rePassword.getBytes());
		this.add(new UserAccount(accountId, accountName, encodePassword));
		userList.add(new User(accountName, encodePassword));
		saveToFile(fBank);
		saveToFile(fUser);
	}

	public void loginAccount() {
		do {
			System.out.print("Enter id of your account (VCBxxxxxx (x is digit)): ");
			accountId = sc.nextLine();
			if (accountId.contains(" "))
				System.out.println("ID can't not contain blank!");
		} while (accountId.contains(" "));

		do {
//		    String password = pass.readPassword("Enter password: ");
//		    System.out.println("The password entered is: "+password);
			System.out.print("Enter your password: ");
			password = sc.nextLine();
			if (password.contains(" "))
				System.out.println("Your password can't not contain blank!");
		} while (accountId.contains(" "));
		String encodedPassword = enc.encodeToString(password.getBytes());
		for (UserAccount o : this) {
			if (o.getAccountId().equals(accountId)) {
				if (o.getPassword().equals(encodedPassword)) {
					successfullyLoggedIn = true;
					System.out.println("Successfully logged in.");
					function = "Login Account";
					logList.add(new Log(accountId, function));
					saveToFile(fLog);
					break;
				}
			}
		}
		if (successfullyLoggedIn == false)
			System.out.println("Wrong password or Id account");
	}

	public void withdrawMoney() {
		double amountWithdraw;
		double calcMoney;
		if (successfullyLoggedIn == true) {
			System.out.println("Please enter the amount money want to withdraw: ");
			amountWithdraw = Double.parseDouble(sc.nextLine());
			for (UserAccount o : this) {
				if (o.getAccountId().equals(accountId)) {
					if (o.getMoney() >= amountWithdraw) {
						calcMoney = o.getMoney() - amountWithdraw;
						o.setMoney(calcMoney);
						function = "Withdraw Money";
						logList.add(new Log(accountId, function));
						saveToFile(fBank);
						saveToFile(fUser);
						saveToFile(fLog);
						break;
					} else
						System.out.println("Your account don't have enough money to withdraw");
				}
			}
		} else
			System.out.println("You need to log in first.");
	}

	public void depositMoney() {
		double amountDeposit;
		double calcMoney;
		String confirm;
		if (successfullyLoggedIn == true) {
			System.out.println("Please enter the amount money want to deposit: ");
			amountDeposit = Double.parseDouble(sc.nextLine());
			System.out.println("Do you sure to process transaction: DEPOSIT: " + amountDeposit + "Y/N ?");
			confirm = sc.nextLine();
			if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("Yes")) {
				for (UserAccount o : this) {
					if (o.getAccountId().equals(accountId)) {
						calcMoney = o.getMoney() + amountDeposit;
						o.setMoney(calcMoney);
						function = "Deposit Money";
						logList.add(new Log(accountId, function));
						saveToFile(fBank);
						saveToFile(fUser);
						saveToFile(fLog);
						break;
					}
				}
			} else
				System.out.println("Transaction failed");
		} else
			System.out.println("You need to log in first.");
	}

	public void transferMoney() {
		String receiveAccountName;
		double amountTransfer;
		boolean isSuccess = false;
		if (successfullyLoggedIn == true) {
			do {
				try {
					System.out.println("Please enter recieve account: ");
					receiveAccountName = sc.nextLine();
					System.out.println("Please enter amount to transfer: ");
					amountTransfer = Double.parseDouble(sc.nextLine());
					if (receiveAccountName.contains(" ")) {
						System.out.println("Your recieve account can't not contain blank!");
					} else {
						for (UserAccount o : this) {
							if (o.getAccountId().equals(accountId) && o.getMoney() >= amountTransfer) {
								for (UserAccount u : this) {
									if (u.getAccountName().equals(receiveAccountName)) {
										double balanceAfter = o.getMoney() - amountTransfer;
										o.setMoney(balanceAfter);
										amountTransfer += u.getMoney();
										u.setMoney(amountTransfer);
										isSuccess = true;
										break;
									}
								}
							}
						}
						if (isSuccess == false) {
							System.out.println("Account does not exist or your money not enough! Check again");
						}
					}
				} catch (Exception e) {
					System.out.println("Your amount should be a number not a String! Try again");
				}
			} while (isSuccess == false);
			function = "Transfer Money";
			logList.add(new Log(accountId, function));
			saveToFile(fBank);
			saveToFile(fUser);
			saveToFile(fLog);

		} else

		{
			System.out.println("You need to log in first.");
		}
	}

	public void removeAccount() {
		if (successfullyLoggedIn == true) {
			boolean isSuccess = false;
			int pos = findId(accountId);
			int findLog = findLog(accountId);
			if (pos >= 0) {
				do {
					try {
						System.out.println("Please type YES to confirm your request and NO to cancel.");
						String confirmCode = sc.nextLine();
						if (confirmCode.equalsIgnoreCase("YES")) {
							this.remove(pos);
							System.out.println(pos);
							userList.remove(pos);
							logList.remove(findLog);
							saveToFile(fBank);
							saveToFile(fUser);
							saveToFile(fLog);
							System.out.println("Account has been removed successfully");
							isSuccess = true;
							successfullyLoggedIn = false;
						} else if (confirmCode.equalsIgnoreCase("NO")) {
							System.out.println("Cancel request!");
							isSuccess = true;
						} else
							System.out.println("Wrong format Just Yes or No !");
					} catch (Exception e) {
						System.out.println(e);
					}
				} while (isSuccess == false);
			}
		} else
			System.out.println("You need to log in first.");
	}

	public void changePassword() {
		if (successfullyLoggedIn == true) {
			String oldPassword;
			String newPassword;
			String renewPassword;
			int pos = findId(accountId);
			System.out.print("Please enter old password: ");
			oldPassword = sc.nextLine();
			System.out.print("Please enter new password: ");
			newPassword = sc.nextLine();
			System.out.print("Please enter new password again: ");
			renewPassword = sc.nextLine();
			String decodedPassword = new String(dec.decode(this.get(pos).getPassword()));
			if (oldPassword.equalsIgnoreCase(decodedPassword)) {
				if (newPassword.equalsIgnoreCase(renewPassword)) {
					String encodePassword = enc.encodeToString(renewPassword.getBytes());
					this.get(pos).setPassword(encodePassword);
					userList.get(pos).setPassword(encodePassword);
					function = "Change Password";
					logList.add(new Log(accountId, function));
					saveToFile(fBank);
					saveToFile(fUser);
					saveToFile(fLog);
					successfullyLoggedIn = false;
				} else {
					System.out.println("Your new password do not match.");
				}
			} else {
				System.out.println("Your old password is not wrong.");
			}

		} else
			System.out.println("You need to log in first.");
	}

	public void printLog() {
		for (Log o : logList)
			if (o.getAccountId().equalsIgnoreCase(accountId)) 
					o.print();
	}

	public void printLogAdmin() {
		for (Log o : logList) {
			o.print();
		}
	}
}
