/*
 * 
 * Description: CPSC314Student is the child class of Student. It holds attributes specific only to students in a 314 class such as which section they are in.
 * Name: Clinton Hastings
 * UIN: 327004056
 * Email: clin10@tamu.edu
 * 
 */
public class CPSC314Student extends Student {
	private int javaKnowledge;																					//Level of Java Knowledge (How groups are decided)
	private int section;																						//Which section the 314 student is in
	
	public CPSC314Student(String firstName, String lastName, String uIN, int javaKnowledge, int section, int rank) {	//Constructor for 314 student
		super(firstName, lastName, uIN, rank);																		//firstName, lastName, UIN handled by parent class "Student"
		this.javaKnowledge = javaKnowledge;															
		this.section = section;
	}
	
	public int getJavaKnowledge() {																				//Allow access to 314 student's java knowledge from outside of class
		return javaKnowledge;
	}
	public void setJavaKnowledge(int javaKnowledge) {															//Change 314 student's current java knowledge (not used)
		this.javaKnowledge = javaKnowledge;
	}
	public int getSection() {																					//Allow access to 314 student's section # from outside of class
		return section;
	}
	public void setSection(int section) {																		//Change 314 student's current section (not used)
		this.section = section;
	}
	
	public String toPrint() {																					//Used to return attributes formatted for output
		return  super.toPrint() + "(" + javaKnowledge + ") ";													//javaKnowledge is the only attribute for output not handled by parent classes
	}

	@Override
	public String toString() {																					//Used for debugging purposes only
		return "CPSC314Student [javaKnowledge=" + javaKnowledge + ", section=" + section + "] is a " + super.toString();
	}

}
