/*
 * 
 * Description: JavaKnowledgeComparator is the comparator used to sort the Students array list. The students are sorted here based on their java knowledge in 
 * 				increasing order.
 * Name: Clinton Hastings
 * UIN: 327004056
 * Email: clin10@tamu.edu
 * 
 */
import java.util.Comparator;
public class JavaKnowledgeComparator implements Comparator<CPSC314Student> {

	@Override
	public int compare(CPSC314Student a, CPSC314Student b) {													//Arranges in ascending order
		if(a.getJavaKnowledge() < b.getJavaKnowledge()) {return -1;}											//Access java knowledge attribute of each 314 Student and compare
		else if(a.getJavaKnowledge() > b.getJavaKnowledge()) {return 1;}
		else {return 0;}																						//(Same java knowledge)
	}

}
