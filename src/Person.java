/*
 * 
 * Description: Person is the parent class of student. It holds very general attributes of people such as name. (only name in this case)
 * Name: Clinton Hastings
 * UIN: 327004056
 * Email: clin10@tamu.edu
 * 
 */
public abstract class Person {
	private String firstName;																					//Person's first name
	private String lastName;																					//Person's last name
	private enum gender {																						//Not used in implementation
		MALE,
		FEMALE
	}
	
	public Person(String firstName, String lastName) {															//Constructor for a Person
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {																				//Allow access to Person's first name
		return firstName;
	}
	public void setFirstName(String firstName) {																//Change Person's first name
		this.firstName = firstName;
	}
	public String getLastName() {																				//Allow access to Person's last name from outside class
		return lastName;
	}
	public void setLastName(String lastName) {																	//Change Person's last name
		this.lastName = lastName;
	}
	
	public String toPrint() {																					//Used to return attributes formatted for output
		return firstName + " " + lastName + " ";																//Final two attributes used for output
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]\n";								//Used for debugging purposes only
	}

	
}
