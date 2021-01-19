package gui;

import java.util.Scanner;

import repo.UserAccountList;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserAccountList ul = new UserAccountList();
		String fName = "bank.dat";
		int choose;
		boolean isLoaded = false;
		do {
			System.out.println("\n--------------------------NGÂN HÀNG CÓ LÀM THÌ MỚI CÓ ĂN-------------------------");
			System.out.println("------------------------KHÔNG LÀM THÌ KHÔNG CÓ TIỀN GỬI NHA------------------------");
			while (isLoaded == false) {
				ul.loadData();
				isLoaded = true;
				break;
			}
			userChoice();
			System.out.print("Please enter your choice from 1 --> 6: ");
			try {
				choose = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Have a nice day.");
				return;
			}
			switch (choose) {

			case 1:
				ul.createNewAccount();
				
				break;

			case 2:
				ul.loginAccount();
				break;

			case 3:
				ul.withdrawMoney();
				break;

			case 4:
				ul.depositMoney();
				break;

			case 5:
				ul.transferMoney();
				break;

			case 6:
				ul.removeAccount();
				break;
			
			case 999: 
				ul.admin();
				break;
			
			default:
				System.out.println("Have a nice day.");
				break;
			}

		} while (choose >= 1 && choose <= 6 || choose == 999);
	}

	public static void userChoice() {
		System.out.println("1. Create new account");
		System.out.println("2. Login function");
		System.out.println("3. Withdrawal function");
		System.out.println("4. Deposit function");
		System.out.println("5. Transfer money");
		System.out.println("6. Remove account");
		System.out.println("Other. Quit");
	}
}
