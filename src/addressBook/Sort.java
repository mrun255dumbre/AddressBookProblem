package addressBook;

import java.util.Comparator;

public class Sort implements  Comparator<Person> {
	
	@Override
	public int compare(Person o1, Person o2) {
		return (o1.getFirstName()).compareTo(o2.getFirstName());
	}
	
}

class SortByZip implements  Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return (o1.getZip()).compareTo(o2.getZip());
	}
}

class SortByCity implements  Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return (o1.getCity()).compareTo(o2.getCity());
	}	
}

class SortByState implements  Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return (o1.getState()).compareTo(o2.getState());
	}
}