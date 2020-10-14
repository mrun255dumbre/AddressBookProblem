package addressBook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookImplementation implements AddressBookInterface{
	String firstName,lastName,address,city,state,zip,phoneNumber;
	ArrayList<Person> addressBookArrayList=new ArrayList<Person>();
	Scanner sc = new Scanner(System.in);
	@Override
	public void addPerson() {
		String new_line="\n";
		String comma=",";
		
		try {
			FileWriter fileWriter = new FileWriter("addressBook.csv");
	        fileWriter.append("FirstName,LastName,Address,State,City,Zip,PhoneNumber");
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
	    	addressBookArrayList.add(new Person(firstName,lastName,address,city,state,zip,phoneNumber));
	    	for (Person person : addressBookArrayList) {
		    	fileWriter.append(new_line);
		    	fileWriter.append(person.getFirstName());
		    	fileWriter.append(comma);
		    	fileWriter.append(person.getLastName());
		    	fileWriter.append(comma);
		    	fileWriter.append(person.getAddress());
		    	fileWriter.append(comma);
		    	fileWriter.append(person.getCity());
		    	fileWriter.append(comma);
		    	fileWriter.append(person.getState());
		    	fileWriter.append(comma);
		    	fileWriter.append(person.getZip());
		    	fileWriter.append(comma);
		    	fileWriter.append(person.getPhoneNumber());
		    	fileWriter.append(comma);
	    	}
	    	fileWriter.flush();
        	fileWriter.close();
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
}
