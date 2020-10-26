package addressBook;

public interface AddressBookInterface {
	public void addPerson(String fileName);
	public void editPerson(String fileName);
	public void deletePerson(String fileName);
	public void sortByName(String fileName);
	public void sort(String fileName);
	public void searchPerson(String fileName);
	public void display(String fileName);
	public void newAddressBook();
	public String selectAddressBook();
}
