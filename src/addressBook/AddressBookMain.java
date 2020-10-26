package addressBook;

import java.util.Scanner;

public class AddressBookMain {
	public static final int ADD_PERSON=1;
	public static final int EDIT_PERSON=2;
	public static final int DELETE_PERSON=3;
	public static final int DISPLAY=4;
	public static final int SORT_BY_NAME=5;
	public static final int SORT=6;
	public static final int SEARCH=7;
	public static final int NEW_ADDRESS_BOOK=8;
	
	public static void main(String[] args) {
		AddressBookInterface addressBookImplementation = new AddressBookImplementation();
		Scanner sc = new Scanner(System.in);
		String fileName;
		while(true) {
			System.out.println("Welcome to Address Book Program");
			System.out.println("\t1.Add Person in address Book\n"
			+"\t2.Edit Person in address Book\n"
			+"\t3.Delete Person in address Book\n"
			+"\t4.Display address Book\n"
			+"\t5.Sort by Name in address Book\n"
			+"\t6.Sort by City,State,Zip in address Book\n"
			+"\t7.Search Person in address Book\n"
			+"\t8.Add New Address Book");
			System.out.println("Enter your choice - ");  
			int choice= sc.nextInt();
			switch(choice) {
				case ADD_PERSON:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.addPerson(fileName);
					break;
				case EDIT_PERSON:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.editPerson(fileName);
					break;
				case DELETE_PERSON:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.deletePerson(fileName);
					break;
				case DISPLAY:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.display(fileName);
					break;
				case SORT_BY_NAME:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.sortByName(fileName);
					break;
				case SORT:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.sort(fileName);
					break;
				case SEARCH:
					fileName = addressBookImplementation.selectAddressBook();
					addressBookImplementation.searchPerson(fileName);
					break;
				case NEW_ADDRESS_BOOK:
					addressBookImplementation.newAddressBook();
					break;
				default:
					System.out.println("Wrong choice! Please select from the above option");
					break;
			}
		}
	}

}
