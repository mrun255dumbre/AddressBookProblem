package addressBook;

import java.util.Scanner;

public class AddressBookMain {
	public static final int ADD_PERSON=1;
	
	public static void main(String[] args) {
		AddressBookImplementation addressBookImplementation = new AddressBookImplementation();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book Program");
		System.out.println("\t1.Add Person in address Book");
		System.out.println("Enter your choice - ");  
		int choice= sc.nextInt();
		switch(choice) {
		case ADD_PERSON:
			addressBookImplementation.addPerson();
			break;
		}
	}

}
