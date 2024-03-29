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
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;


public class AddressBookImplementation implements AddressBookInterface {
	String firstName,lastName,address,city,state,zip,phoneNumber;
	int flag = 0;
	public static File file;
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	ArrayList<Person> addressBookArrayList=new ArrayList<Person>();
	ArrayList<Person> addPerson=new ArrayList<Person>();
	Scanner scanner = new Scanner(System.in);
	public static List<Person> list = new ArrayList<Person>();

	@Override
	public void addPerson(String fileName) {
		String new_line="\n";
		String comma=",";
		int personExists = 0;
		
		try {
			FileWriter fileWriter = new FileWriter(fileName+".csv",true);
			System.out.println("How many contacts you want to add? :");
            int numberOfContacts=scanner.nextInt();
            
            for(int i = 0; i < numberOfContacts; i++) {
				System.out.println("Enter First Name : ");
		    	firstName=scanner.next();
		    	
		    	personExists = (int)personList(fileName).stream().filter(searchString -> searchString.getFirstName().equals(firstName)).count();
		    	if(personExists != 0) {
		    		System.out.println("Person already exists :"+firstName);
		    		fileWriter.close();
		    		break;
		    	}
		    	
		    	System.out.println("Enter Last Name : ");
		    	lastName=scanner.next();
	
		    	System.out.println("Enter Address : ");
		    	address=scanner.next();
	
		    	System.out.println("Enter City : ");
		    	city=scanner.next();
	
		    	System.out.println("Enter State : ");
		    	state=scanner.next();
	
		    	System.out.println("Enter Zipcode : ");
		    	zip=scanner.next();
	
		    	System.out.println("Enter Phone Number : ");
		    	phoneNumber=scanner.next();
		    	addPerson.add(new Person(firstName,lastName,address,city,state,zip,phoneNumber));
            }
            
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
		    System.out.println("Person record added successfully!!");
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
		String lineToFind = scanner.next();
		String line = null;
		File inFile = new File((fileName+".csv"));
		File tempFile = new File("temp.csv");
		
		try {
			bufferedReader = new BufferedReader(new FileReader(inFile));
			bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
			
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().contains(lineToFind)) {
					
					System.out.println("Record for given phone number\n" + line);
					
					String[] persondetails = line.split(",");
					
					String firstname = persondetails[0];
					String lastname = persondetails[1];
					
					System.out.println("enter the address");
					String address = scanner.next();
					
					System.out.println("enter the city");
					String city = scanner.next();
					
					System.out.println("enter the State");
					String state = scanner.next();
					
					System.out.println("enter the Zipcode");
					int zipCode = scanner.nextInt();
					
					System.out.println("enter the Phone Number");
					String phoneNumber = scanner.next();
					
					bufferedWriter.write(firstname);
					bufferedWriter.write("," + lastname);
					bufferedWriter.write("," + address);
					bufferedWriter.write("," + city);
					bufferedWriter.write("," + state);
					bufferedWriter.write("," + zipCode);
					bufferedWriter.write("," + phoneNumber);
					bufferedWriter.newLine();
					flag++;
				} 
				else {
					bufferedWriter.write(line);
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
			bufferedReader.close();
			
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
		String dataToRemove = scanner.next();
		
		File inFile = new File((fileName+".csv"));
		File tempFile = new File("temp.csv");
		
		try {
			bufferedReader = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (!line.trim().contains(dataToRemove)) {
					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			bufferedReader.close();

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
		int choice= scanner.nextInt();
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
		search = scanner.next();
		personList(fileName).stream().filter(searchString -> searchString.getCity().equalsIgnoreCase(search)||searchString.getState().trim().equalsIgnoreCase(search)).forEach(System.out::println);
		addressBookArrayList.clear();
	}
	
	@Override
	public void newAddressBook() throws Throwable {
		int flag=0;
		System.out.print("Enter Name for Address Book: ");
		String newfileName = scanner.next();
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
				
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write("First_Name");
				bufferedWriter.write(",Last_name");
				bufferedWriter.write(",Address");
				bufferedWriter.write(",City");
				bufferedWriter.write(",State");
				bufferedWriter.write(",Zipcode");
				bufferedWriter.write(",Phone_Number");
				bufferedWriter.newLine();
				System.out.println("Address Book Is Succesfully Created ");
				bufferedWriter.close();
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
        }catch (Exception exception) 
		{
            System.err.println(exception.getMessage());
        }
		System.out.println("Please enter Address Book name -");
		String fileName = scanner.next();
		file = new File(fileName+".csv");
		if (file.isFile()) {
			return fileName;
		} else

		return null;
	}
	
	public ArrayList<Person> personList(String fileName) {
		File inFile = new File((fileName+".csv"));
		
		try {
			
			bufferedReader = new BufferedReader(new FileReader(inFile));
			
			String currentLine = bufferedReader.readLine();
			
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
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (FileNotFoundException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} 
		return addressBookArrayList;
	}
	
	
	@SuppressWarnings("unchecked")
	public void addJsonFile(String fileName) {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("Person",personList(fileName));
        try (FileWriter file = new FileWriter(fileName+".json")) {
        	 
            file.write(jsonObject.toJSONString());
            file.flush();
 
        } catch (IOException exception) {
        	exception.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);
        addressBookArrayList.clear();
	}
	
}
