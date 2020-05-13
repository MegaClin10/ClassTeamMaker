/*
 * 
 * Description: Student is child class of Person and parent class of CPSC314Student. It holds attributes specific to student at TAMU such as UIN
 * Name: Clinton Hastings
 * UIN: 327004056
 * Email: clin10@tamu.edu
 * 
 */
public class Student extends Person {
	private String UIN;																							//Student's current UIN
	private enum Rank {																							//enum object to for different grade classifications
		FRESHMAN,
		SOPHOMORE,
		JUNIOR,
		SENIOR	
	}
	Rank rank;																									//Rank rank holds Student's grade classification
	
	public Student(String firstName, String lastName, String uIN, int rank) {									//Constructor for a Student
		super(firstName, lastName);																				//firstName, lastName handled by parent class
		UIN = uIN;
		switch (rank) {																							//rank is an enum type so each valid int number corresponds to a grade classification																		//Invalid ranks caught in Utility are auto-changed to "-1" since rank is not vital to team creation
			case 1: this.rank = Rank.FRESHMAN; break;
			case 2: this.rank = Rank.SOPHOMORE; break;
			case 3: this.rank = Rank.JUNIOR; break;
			case 4: this.rank = Rank.SENIOR; break;
			default: this.rank = null;																			//Should never get to this point
		}
	}
	
	public String getUIN() {																					//Allow access to Student's UIN from outside of class
		return UIN;
	}
	public void setUIN(String uIN) {																			//Change student's current UIN
		UIN = uIN;
	}
	public Rank getRank() {																						//Allow access to Student's rank from outside class
		return rank;
	}
	public void setRank(int newRank) {																			//Change student's current rank
		switch (newRank) {																						//Switch statement to set rank based on int input
			case 1: rank = Rank.FRESHMAN; break;
			case 2: rank = Rank.SOPHOMORE; break;
			case 3: rank = Rank.JUNIOR; break;
			case 4: rank = Rank.SENIOR; break;
			default: rank = null;
		}
	}
	
	public String toPrint() {																					//Used to return attributes formatted for output
		return super.toPrint();																					//All attributes for output are handled by parent classes
	}

	@Override
	public String toString() {																					//Used for debugging purposes only
		return "Student [UIN=" + UIN + ", rank=" + rank + "] is a " + super.toString();
	}
	
}
