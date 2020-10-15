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
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AddressBookImplementation implements AddressBookInterface {
	String firstName,lastName,address,city,state,zip,phoneNumber;
	int flag = 0;
	public static File file;
	BufferedReader br;
	BufferedWriter bw;
	ArrayList<Person> addressBookArrayList=new ArrayList<Person>();
	ArrayList<Person> lines=new ArrayList<Person>();
	Scanner sc = new Scanner(System.in);
	@Override
	public void addPerson() {
		String new_line="\n";
		String comma=",";
		boolean personExists=false;
		
		try {
			FileWriter fileWriter = new FileWriter("addressBook.csv");
			fileWriter.append("FirstName,LastName,Address,City,State,Zip,PhoneNumber");
			System.out.println("How many contacts you want to add? :");
            int numberOfContacts=sc.nextInt();
            
            for(int i = 0; i < numberOfContacts; i++) {
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
            
            Scanner scanner = new Scanner(new File("addressBook.csv"));
            while (scanner.hasNextLine()) {
    			String lineToFind = scanner.nextLine();
    			if (lineToFind.trim().contains(firstName)) {
    				System.out.println("Person already exists"+lineToFind);
    			}
    			else {
    				personExists=true;
    			}
    		}
            
            if(!personExists) {
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
	
	
	@Override
	public void sortByName(String fileName) {
			Collections.sort(personList(fileName), new Sort());
			System.out.println("Records after Sort By Name: ");
			for (Person P : lines) {
				System.out.println(P.getFirstName() + " " + P.getLastName() + " " + P.getAddress() + " " + P.getCity() + " " + P.getState() + " "
						+ P.getZip() + " " + P.getPhoneNumber());
			}
			System.out.println("");
	}
	
	@Override
	public void sort(String fileName) {
		final int SORT_CITY=1;
		final int SORT_STATE=2;
		final int SORT_ZIP=3;
		System.out.println("\t1.Sort by City in address Book\n"
		+"\t2.Sort by State in address Book\n"
		+"\t3.Sort by Zip in address Book");
		System.out.println("Enter your choice - ");  
		int choice= sc.nextInt();
		switch(choice) {
			case SORT_CITY:
				sortByCity(fileName);
				break;
			case SORT_STATE:
				sortByState(fileName);
				break;
			case SORT_ZIP:
				sortByZip(fileName);
				break;
			default:
				System.out.println("Wrong choice! Please select from the above option");
				break;
		}
	}
	
	public void sortByZip(String fileName) {
			Collections.sort(personList(fileName), new SortByZip());
			System.out.println("Data after Sort By Zip: ");
			for (Person P : lines) {
				System.out.println(P.getFirstName() + " " + P.getLastName() + " " + P.getAddress() + " " + P.getCity() + " " + P.getState() + " "
						+ P.getZip() + " " + P.getPhoneNumber());
			}
			System.out.println("");
	}
	
	public void sortByCity(String fileName) {
		Collections.sort(personList(fileName), new SortByCity());
		System.out.println("Data after Sort By City: ");
		for (Person P : lines) {
			System.out.println(P.getFirstName() + " " + P.getLastName() + " " + P.getAddress() + " " + P.getCity() + " " + P.getState() + " "
					+ P.getZip() + " " + P.getPhoneNumber());
		}
		System.out.println("");
	}
	
	public void sortByState(String fileName) {
		Collections.sort(personList(fileName), new SortByState());
		System.out.println("Data after Sort By State: ");
		for (Person P : lines) {
			System.out.println(P.getFirstName() + " " + P.getLastName() + " " + P.getAddress() + " " + P.getCity() + " " + P.getState() + " "
					+ P.getZip() + " " + P.getPhoneNumber());
		}
		System.out.println("");
	}
	
	public ArrayList<Person> personList(String fileName) {
		File inFile = new File((fileName+".csv"));
		
		try {
		
			br = new BufferedReader(new FileReader(inFile));
			String currentLine = br.readLine();
		
			while (currentLine != null) {
				String[] persondetails = currentLine.split(",");
				String firstname = persondetails[0];
				String lastname = persondetails[1];
				String address = persondetails[2];
				String city = persondetails[3];
				String state = persondetails[4];
				String zipcode = persondetails[5];
				String phonenumber = persondetails[6];
				lines.add(new Person(firstname, lastname,address, city, state, zipcode, phonenumber));
				currentLine = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return lines;
	}
	
}
