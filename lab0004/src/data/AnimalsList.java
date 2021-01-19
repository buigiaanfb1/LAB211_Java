package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
//import java.util.Iterator;

public class AnimalsList extends ArrayList<Animals> {
	Scanner sc = new Scanner(System.in);

	public void loadData(String fName) {
		importFile(fName);
		System.out.println("All your data animals is loaded!\n");
	}

////////////////////////////\\\\\\\/////////////////CHECK VALIDATE////////////////\\\\\\\////////////////////////
	private int checkValid(String aName) {
		if (aName.equals("") || aName.equals(" "))
			return -1;
		return 1;
	}

////////////////////////////\\\\\\/////////////////FIND ID////////////////\\\\\\\\////////////////////////
	private int findId(String aCode) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getId().equalsIgnoreCase(aCode))
				return i;
		}
		return -1;
	}
	
	
////////////////////////////\\////////////////IMPORT FILE/////////////////\\////////////////////////
	public void importFile(String fName) {
		try {
			File f = new File(fName);
			if (!f.exists()) {
				System.out.println("Empty list.");
				return;
			}
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String details;
			while ((details = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(details, "|");
				String id = st.nextToken().toUpperCase();
				String name = st.nextToken().toUpperCase();
				String attribute = st.nextToken().toUpperCase();
				double weight = Double.parseDouble(st.nextToken());
				int type = Integer.parseInt(st.nextToken());
				this.add(new Animals(id, name, attribute, weight, type));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

////////////////////////////\\\\/////////////////SAVE FILE////////////////\\\\\\\////////////////////////
	public void saveToFile(String fName) {
		if (this.size() == 0) {
			System.out.println("Empty list");
			return;
		}
		try {
			File f = new File(fName);
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			for (Animals o : this) {
				pw.println(o.getId().toUpperCase() + "|" + o.getName().toUpperCase() + "|"
						+ o.getAttribute().toUpperCase() + "|" + o.getWeight() + "|" + o.getType() + "|");
			}
			pw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

////////////////////////////\\\\\\\\/////////////////ADD NEW ANIMALS////////////////\\\\////////////////////////
	public void addNewAnimals() {
		String name;
		String id;
		String attribute;
		double weight;
		int type;
		int pos;
		boolean valid = true;
		while (true) {
			try {
				System.out.print("Enter type of animals: ");
				type = Integer.parseInt(sc.nextLine());
				if (type >= 1 && type <= 4) {
					break;
				} else
					System.out.println("Please input between 1 --> 4.");
			} catch (Exception e) {
				System.out.println("Cannot add info. Please try again.");
			}
		}

		do {
			System.out.print("Enter ID of animals (TZMxxxxxx (x is digit)): ");
			id = sc.nextLine();
			pos = findId(id);
			valid = id.matches("^TZM\\d{6}$");
			if (pos >= 0)
				System.out.println("This ID is duplicated.");
			else if (id.contains(" ")) {
				System.out.println("ID does not contain blank.");
			} else if (!valid)
				System.out.println("ID must match (TZMxxxxxx (x is digit))! Try again.");
		} while (pos >= 0 || id.contains(" ") || !valid);
		System.out.print("Enter name of animals: ");
		name = sc.nextLine();
		System.out.print("Enter atrribute of animals: ");
		attribute = sc.nextLine();
		while (true) {
			try {
				System.out.print("Enter weight of animals: ");
				weight = Double.parseDouble(sc.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Wrong format. Try Again!");
			}
		}
		System.out.print("Successfully added!");
		this.add(new Animals(id, name, attribute, weight, type));
	}

/////////////////////\////////////////////\\\////////////////////UPDATE////////////////////\\\\\\////////////////////
	public void updateAnimals() {
		String id;
		System.out.print("Please enter id of animals to change!");
		id = sc.nextLine();
		int pos = findId(id);
		if (pos < 0)
			System.out.println("Animal does not exist!");

/////////////////////NAME////////////////////	

		else {
			String name = this.get(pos).getName();
			System.out.print("Old name is:" + name + ", New name is: ");
			String newName = sc.nextLine();
			int checkName = checkValid(newName);
			if (checkName > 0) {
				this.get(pos).setName(newName);
				System.out.println("Updated");
			}

/////////////////////ATTRIBUTE////////////////////			
			String attribute = this.get(pos).getAttribute();
			System.out.print("Old atrribute is:" + attribute + ", New attribute is: ");
			String newAttribute = sc.nextLine();
			int checkAttribute = checkValid(newAttribute);
			if (checkAttribute > 0) {
				this.get(pos).setName(newAttribute);
				System.out.println("Updated");
			}

/////////////////////WEIGHT////////////////////			

			while (true) {
				double weight = this.get(pos).getWeight();
				try {
					System.out.print("Old weight is:" + weight + ", New weight is: ");
					String newWeightString = sc.nextLine();
					int checkWeight = checkValid(newWeightString);
					double newWeight;
					if (checkWeight > 0) {
						newWeight = Double.parseDouble(newWeightString);
						if (newWeight > 0) {
							this.get(pos).setWeight(newWeight);
							System.out.println("Updated");
							break;
						}
					} else {
						System.out.println("Nothing new!");
						break;
					}
				} catch (Exception e) {
					System.out.println("Must be a number. Please try again!");
				}
			}

///////////////////TYPE////////////////////

			while (true) {
				int type = this.get(pos).getType();
				try {
					System.out.print("Old type is:" + type + ", New type is: ");
					String newTypeString = sc.nextLine();
					int checkType = checkValid(newTypeString);
					int newType;
					if (checkType > 0) {
						newType = Integer.parseInt(newTypeString);
						if (newType > 0 && newType <= 4) {
							this.get(pos).setType(newType);
							System.out.print("Updated");
							break;
						}
					} else {
						System.out.println("Nothing new!");
						break;
					}
				} catch (Exception e) {
					System.out.println("Must be a number. Please try again!");
				}
			}
			System.out.println("\nAll information is updated!\n");
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////UPDATE////////////////////UPDATE////////////////////UPDATE////////////////////UPDATE////////////////////
	public void removeAnimals() {
		String id;
		System.out.print("Please enter id of animals to remove!");
		id = sc.nextLine();
		int pos = findId(id);
		if (pos < 0)
			System.out.println("Animal does not exist! Remove failed");
		else {
			System.out.println("Please type " + id + " to confirm.");
			String confirmCode = sc.nextLine();
			if (confirmCode.equalsIgnoreCase(id)) {
				this.remove(pos);
				System.out.println("Animal with ID: " + id + " has been removed.");
			} else
				System.out.println("Wrong code. Remove failed");
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////SEARCH////////////////////SEARCH////////////////////SEARCH////////////////////SEARCH////////////////////
	public void searchAnimalsByName() {
		boolean isHave = false;
		System.out.print("Enter name of animals to search: ");
		String name = sc.nextLine();
		for (Animals o : this) {
			if (o.getName().equalsIgnoreCase(name)) {
				o.print();
				isHave = true;
			}
		}
		if (isHave == false) {
			System.out.println("Dont have animal with the name " + name + " in the list");
		}
	}

	public void searchAnimalsByWeight() {
		double lower, upper;
		while (true) {
			try {
				System.out.print("Enter lower bound of the desired weight to search: ");
				lower = Double.parseDouble(sc.nextLine());
				if (lower <= 0)
					System.out.println("Lower bound must higher than 0");
				else
					break;
			} catch (Exception e) {
				System.out.println("Wrong format! Must be a number!");
			}
		}
		while (true) {
			try {
				System.out.print("Enter upper bound of the desired weight to search: ");
				upper = Double.parseDouble(sc.nextLine());
				if (upper < lower)
					System.out.println("Upper bound must higher than lower bound.");
				else
					break;
			} catch (Exception e) {
				System.out.println("Wrong format! Must be a number!");
			}
		}
		for (Animals o : this) {
			if (o.getWeight() >= lower && o.getWeight() <= upper) {
				o.print();
			}
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////PRINT////////////////////PRINT////////////////////PRINT////////////////////PRINT////////////////////

	public void showByType() {
		boolean isHave = false;
		while (true) {
			try {
				System.out.println("Enter type you wanna print!");
				int type = Integer.parseInt(sc.nextLine());
				if (type >= 1 && type <= 4) {
					for (Animals o : this) {
						if (o.getType() == type) {
							o.print();
							isHave = true;
						}
					}
					if (isHave == false) {
						System.out.println("There is no animals of type" + type + "in the zoo!");
						break;
					} else
						break;
				} else {
					System.out.println("Please input between 1 --> 4");
				}
			} catch (Exception e) {
				System.out.println("Wrong format! Try Again");
			}
		}
	}

	public void showAll() {
		if (this.size() == 0) {
			System.out.println("Empty list. Check your file again!");
			return;
		}
		System.out.println("\n-----------------------------LIST OF ANIMALS-----------------------------");
		System.out.println("-------------------------------------------------------------------------");
		for (Animals o : this)
			o.print();
	}
}
