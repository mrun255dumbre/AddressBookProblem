package addressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookImplementation implements AddressBookInterface{
	String firstName,lastName,address,city,state,zip,phoneNumber;
	int flag = 0;
	public static File file;
	BufferedReader br;
	BufferedWriter bw;
	ArrayList<Person> addressBookArrayList=new ArrayList<Person>();
	Scanner sc = new Scanner(System.in);
	@Override
	public void addPerson() {
		String new_line="\n";
		String comma=",";

		try {
			FileWriter fileWriter = new FileWriter("addressBook.csv");
			System.out.println("How many contacts you want to add? :");
            int numberOfContacts=sc.nextInt();
            
            for(int i = 0; i < numberOfContacts; i++) {
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
            }
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
	@Override
	public void editPerson(String fileName) {
		System.out.println("Enter phone number for edit person data\n");
		String lineToFind = sc.next();
		String line = null;
		File inFile = new File((fileName+".csv"));
		File tempFile = new File("temp.csv");
		
		try {
			br = new BufferedReader(new FileReader(inFile));
			bw = new BufferedWriter(new FileWriter(tempFile));
			
			while ((line = br.readLine()) != null) {
				if (line.trim().contains(lineToFind)) {
					
					System.out.println("Record for given phone number\n" + line);
					
					String[] persondetails = line.split(",");
					
					String firstname = persondetails[0];
					String lastname = persondetails[1];
					
					System.out.println("enter the address");
					String address = sc.next();
					
					System.out.println("enter the city");
					String city = sc.next();
					
					System.out.println("enter the State");
					String state = sc.next();
					
					System.out.println("enter the Zipcode");
					int zipcode = sc.nextInt();
					
					String phonenumber = persondetails[6];
					
					bw.write(firstname);
					bw.write("," + lastname);
					bw.write("," + address);
					bw.write("," + city);
					bw.write("," + state);
					bw.write("," + zipcode);
					bw.write("," + phonenumber);
					bw.newLine();
					flag++;
				} 
				else {
					bw.write(line);
					bw.newLine();
				}
			}
			bw.close();
			br.close();
			
			inFile.delete();
			tempFile.renameTo(inFile);
			
			if (flag == 0)
				System.out.println("Record found in AddressBook :" + fileName);
			else
				System.out.println("Record Edited Successfully");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deletePerson(String fileName) {
		System.out.println("Enter Phone Number to Delete Record");
		String dataToRemove = sc.next();
		
		File inFile = new File((fileName+".csv"));
		File tempFile = new File("temp.csv");
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.trim().contains(dataToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			inFile.delete();
			
			System.out.println("Data deleted From AddressBook");
			tempFile.renameTo(inFile);
			System.out.println(" ");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void display(String fileName) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Records Present in System :");
		try {
			scanner = new Scanner(new File(fileName+".csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
	}
	
}
