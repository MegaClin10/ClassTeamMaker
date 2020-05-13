/*
 * 
 * Description: TeamBuilder calls every other function for its own use. It is much like a main. It will take the input provided by Utility, create sections for each
 * 				unique section, place students in their respective sections, and then sort each sections' students separately using JavaKnowledgeComparator. Finally, 
 * 				Utility will be called on once again to provide the output for the program.
 * Name: Clinton Hastings
 * UIN: 327004056
 * Email: clin10@tamu.edu
 * 
 */
import java.util.*;
public class TeamBuilder {
	private CPSC314Student teams;																				//Not used in implementation
	private ArrayList<CPSC314Student> allStudents;																//ArrayList to hold all students in 314
	private Utility util;																						//Utility to use read and write functions
	private JavaKnowledgeComparator compare;																	//Comparator used to sort students by knowledge
	
	public TeamBuilder() {
		allStudents = new ArrayList<CPSC314Student>();															//Initialize allStudents
		util = new Utility();																					//Initialize util
		compare = new JavaKnowledgeComparator();																//Initialize compare
		util.readFile(allStudents);																				//Call readFile function to populate allStudents
		
		ArrayList<Integer> Sections = new ArrayList<Integer>();													//Sections holds all the unique section numbers
		for(int i = 0; i < allStudents.size(); i ++) {															//Iterate through allStudents
			if(!Sections.contains(allStudents.get(i).getSection()))												//If student's section does not already exist
				Sections.add(allStudents.get(i).getSection());													//Then add section to Sections
		}
		ArrayList<ArrayList<CPSC314Student>> studentSections = new ArrayList<ArrayList<CPSC314Student>>();		//2D Array to help organize students by sections
		for(int i = 0; i < Sections.size(); i++) {																//Iterate through all sections
			ArrayList<CPSC314Student> sec = new ArrayList<CPSC314Student>();									//Create "temp" list to be added to 2D Array
			studentSections.add(sec);																			//Add this list (^) list to studentSectoins Array
			for(int j = 0; j < allStudents.size(); j++) {														//Iterate through all Students in allStudents
				if(allStudents.get(j).getSection() == Sections.get(i))											//If section of Student and section match
					studentSections.get(i).add(allStudents.get(j));												//Then add that student to the array
			}
		}
		for(int i = 0; i < studentSections.size(); i++)															//Iterates through all unique section lists
			Collections.sort(studentSections.get(i), compare);													//Sort students in this section

		util.writeResults(studentSections, Sections);															//Call writeResults function to output results
	}

	public CPSC314Student getTeams() {																			//Not used in implementation
		return teams;
	}
	public void setTeams(CPSC314Student teams) {																//Not used in implementation
		this.teams = teams;
	}
	public Utility getUtil() {																					//Allow access to utility currently being used
		return util;
	}
	public void setUtil(Utility util) {																			//Change the utility used to handle file IO
		this.util = util;
	}
	public JavaKnowledgeComparator getCompare() {																//Allow access to comparator currently being used 
		return compare;
	}
	public void setCompare(JavaKnowledgeComparator compare) {													//Change comparator used for comparing students
		this.compare = compare;
	}
	
	@Override
	public String toString() {																					//Used for debugging purposes only
		return teams.toString();
	}
}
