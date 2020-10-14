package addressBook;

import java.util.Scanner;

public class AddressBookMain {
	public static final int ADD_PERSON=1;
	public static final int EDIT_PERSON=2;
	public static final int DELETE_PERSON=3;
	public static final int DISPLAY=4;
	
	public static void main(String[] args) {
		AddressBookImplementation addressBookImplementation = new AddressBookImplementation();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Welcome to Address Book Program");
			System.out.println("\t1.Add Person in address Book\n"
			+"\t2.Edit Person in address Book\n"
			+"\t3.Delete Person in address Book\n"
			+"\t4.Display address Book");
			System.out.println("Enter your choice - ");  
			int choice= sc.nextInt();
			switch(choice) {
				case ADD_PERSON:
					addressBookImplementation.addPerson();
					break;
				case EDIT_PERSON:
					addressBookImplementation.editPerson("addressBook");
					break;
				case DELETE_PERSON:
					addressBookImplementation.deletePerson("addressBook");
					break;
				case DISPLAY:
					addressBookImplementation.display("addressBook");
					break;
				default:
					System.out.println("Wrong choice! Please select from the above option");
					break;
			}
		}
	}

}
