package runtime;

import java.util.Scanner;
import data.AnimalsList;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String fName = "animals.txt";
		int choose = 0;
		boolean isLoaded = false;
		AnimalsList al = new AnimalsList();
		do {
			System.out.println("\n-----------------------------ZOO MANAGEMENT SYSTEM-----------------------------");
			System.out.println("-------------------------------------------------------------------------------");
			while (isLoaded == false) {
				al.loadData(fName);
				isLoaded = true;
				break;
			}
			getUserChoice();
			while (true) {
				try {
					choose = sc.nextInt();
					break;
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			switch (choose) {
			case 1:
				String continueInput;
				al.addNewAnimals();
				while (true) {
					System.out.print("Do you want to add more animal? (Y/N)");
					continueInput = sc.nextLine();
					if (continueInput.equalsIgnoreCase("N") || continueInput.equalsIgnoreCase("no")) {
						break;
					} else if (continueInput.equalsIgnoreCase("Y") || continueInput.equalsIgnoreCase("yes")) {
						al.addNewAnimals();
					} else {
						System.out.println("You typed wrong syntax! Just Yes or No!");
					}
				}
				break;
			case 2:
				al.updateAnimals();
				break;
			case 3:
				al.removeAnimals();
				break;
			case 4:
				int inputSearch;
				do {
					System.out.println("----------SEARCH ANIMALS----------");
					System.out.println("------------------------------------");
					searchAnimals();
					inputSearch = sc.nextInt();
					switch (inputSearch) {
					case 1:
						al.searchAnimalsByName();
						break;
					case 2:
						al.searchAnimalsByWeight();
						break;
					default:
						System.out.println("Back to Main Menu!");
					}
				} while (inputSearch >= 1 && inputSearch <= 2);

				break;
			case 5:
				int inputShow;
				do {
					System.out.println("------------SHOW ANIMALS-----------");
					System.out.println("------------------------------------");
					showAnimals();
					inputShow = sc.nextInt();
					switch (inputShow) {
					case 1:
						al.showByType();
						break;
					case 2:
						al.showAll();
						break;
					default:
						System.out.println("Back to Main Menu!");
					}
				} while (inputShow >= 1 && inputShow <= 2);
				break;
			case 6:
				al.saveToFile(fName);
				break;
			default:
				System.out.printf("Have a nice day");
				return;
			}
		} while (choose >= 1 && choose <= 6);
	}

	public static void getUserChoice() {
		System.out.println("1. Add new animal");
		System.out.println("2. Update animal");
		System.out.println("3. Delete animal");
		System.out.println("4. Search animal");
		System.out.println("5. Show animal list");
		System.out.println("6. Store data to file");
		System.out.println("Press anykey not in the list to QUIT!");

	}

	public static void searchAnimals() {
		System.out.println("1. Search by name");
		System.out.println("2. Search by weight");
		System.out.println("3. Back to ZOO MANAGEMENT SYSTEM");
	}

	public static void showAnimals() {
		System.out.println("1. Show by type");
		System.out.println("2. Show all");
		System.out.println("3. Back to ZOO MANAGEMENT SYSTEM");
	}
}
