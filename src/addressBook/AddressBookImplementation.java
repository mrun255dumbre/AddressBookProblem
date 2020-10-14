package addressBook;

import java.util.Scanner;

public class AddressBookImplementation implements AddressBookInterface{
	String firstName,lastName,address,city,state,zip,phoneNumber;
	Scanner sc = new Scanner(System.in);
	@Override
	public void addPerson() {
		
		System.out.println("Enter First Name : ");
    	firstName=sc.next();
    	
    	System.out.println("Enter Last Name : ");
    	lastName=sc.next();
    	
    	System.out.println("Enter Address : ");
    	address=sc.next();
    	
    	System.out.println("Enter City : ");
    	city=sc.next();
    	
    	System.out.println("Enter State : ");
    	state=sc.next();
    	
    	System.out.println("Enter Zipcode : ");
    	zip=sc.next();
    	
    	System.out.println("Enter Phone Number : ");
    	phoneNumber=sc.next();
    	
	}
	
}
