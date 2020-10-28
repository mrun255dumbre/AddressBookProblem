package addressBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookImplementation implements AddressBookInterface {
	String firstName,lastName,address,city,state,zip,phoneNumber;
	int flag = 0;
	public static File file;
	BufferedReader br;
	BufferedWriter bw;
	ArrayList<Person> addressBookArrayList=new ArrayList<Person>();
	ArrayList<Person> addPerson=new ArrayList<Person>();
	Scanner sc = new Scanner(System.in);
	
	
	@Override
	public void addPerson(String fileName) {
		String new_line="\n";
		String comma=",";
		int personExists = 0;
		
		try {
			FileWriter fileWriter = new FileWriter(fileName+".csv",true);
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
		    	addPerson.add(new Person(firstName,lastName,address,city,state,zip,phoneNumber));
            }
            
            personExists = (int)personList(fileName).stream().filter(searchString -> searchString.getFirstName().equals(firstName)).count();
            
            if(personExists == 0) {
		    	for (Person person : addPerson) {
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
			    	fileWriter.append(new_line);
		    	}
            }
	    	fileWriter.flush();
        	fileWriter.close();
        	addressBookArrayList.clear();
        	addPerson.clear();
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
		personList(fileName).stream().forEach(System.out::println);
		addressBookArrayList.clear();
	}
	
	@Override
	public void sortByName(String fileName) {
		personList(fileName).stream().sorted((s1, s2) -> (s1.getFirstName()).compareTo(s2.getFirstName())).forEach(System.out::println);
		addressBookArrayList.clear();
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
		personList(fileName).stream().sorted((s1, s2) -> (s1.getZip()).compareTo(s2.getZip())).forEach(System.out::println);
		addressBookArrayList.clear();
	}
	
	public void sortByCity(String fileName) {
		personList(fileName).stream().sorted((s1, s2) -> (s1.getCity()).compareTo(s2.getCity())).forEach(System.out::println);
		addressBookArrayList.clear();
	}
	
	public void sortByState(String fileName) {
		personList(fileName).stream().sorted((s1, s2) -> (s1.getState()).compareTo(s2.getState())).forEach(System.out::println);
		addressBookArrayList.clear();
	}
	
	@Override
	public void searchPerson(String fileName) {
		String search;
		System.out.println("Please enter city or state for search :");
		search = sc.next();
		personList(fileName).stream().filter(searchString -> searchString.getCity().trim().equalsIgnoreCase(search)||searchString.getState().trim().equalsIgnoreCase(search)).forEach(System.out::println);
		addressBookArrayList.clear();
	}
	
	@Override
	public void newAddressBook() {
		int flag=0;
		System.out.print("Enter Name for Address Book: ");
		String newfileName = sc.next();
		FileWriter fileWriter;
		newfileName = newfileName+".csv";
		file = new File(newfileName);
		
    	if(file.exists()) {
    		System.out.println("File name already exists");
    		newAddressBook();
    	}else {
    		flag=1;
    	}
		
		if (flag == 1) 
		{
			
			try {
				
				file.createNewFile();
				fileWriter = new FileWriter(file);
				
				bw = new BufferedWriter(fileWriter);
				bw.write("First_Name");
				bw.write(",Last_name");
				bw.write(",Address");
				bw.write(",City");
				bw.write(",State");
				bw.write(",Zipcode");
				bw.write(",Phone_Number");
				bw.newLine();
			
				System.out.println("Address Book Is Succesfully Created ");
				bw.close();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Some Error Occure During the input");	
				e.printStackTrace();
			}
		}else {
			System.out.println("Unable to create An AdressBook");
		}
	}
	
	public String selectAddressBook(){
		
		System.out.println("List of Present Address Book - ");
		try 
		{
            File file = new File(".");
 
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File f, String name) {
                    return name.endsWith(".csv");
                }
            };
            
            File[] files = file.listFiles(filter);
            for (int i = 0; i < files.length; i++) 
            {
                System.out.println("\t\t"+files[i].getName());
            }
        }catch (Exception e) 
		{
            System.err.println(e.getMessage());
        }
		System.out.println("Please enter Address Book name -");
		String fileName = sc.next();
		file = new File(fileName+".csv");
		if (file.isFile()) {
			return fileName;
		} else

			return null;
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
				addressBookArrayList.add(new Person(firstname, lastname,address, city, state, zipcode, phonenumber));
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
		return addressBookArrayList;
	}
	
}
