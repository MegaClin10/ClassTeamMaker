/*
 * 
 * Description: Utility handles all code that requires accessing of any files. 
 * 				This class takes an input (csv) file and goes through all of it and stores each entry in the correct variables which are then assigned to a student.
 * 				This is all done while looking for possible errors in entries. If any are found, the error messages are output to the error log and the entry is skipped.
 * 				Additionally this class will output the sorted teams using each students formatted toPrint() functions.
 * Name: Clinton Hastings
 * UIN: 327004056
 * Email: clin10@tamu.edu
 * 
 */
import java.io.*;
import java.util.*;
public class Utility {
	
	boolean readFile(ArrayList<CPSC314Student> StudentList) {
		
		boolean debug = false;																					//Toggle debugging output
		
		PrintWriter outFile = null;
		try {																									//Try to open new output file with given name
			outFile = new PrintWriter("ErrorLog.txt");
		}
		catch(FileNotFoundException e) {																		//If file is not found
			System.out.println("File not found");																//Print to CONSOLE since error log file cannot be opened
			e.printStackTrace();																				//Print to CONSOLE stack trace
			return false;																						//Function run failed, returned false
		}
		
		Scanner inFile = null;
		try {																										//Try to open new input file with given name
			inFile = new Scanner(new FileReader("CSCE 314 Spring 2020 Project 1 Survey Data  - Form Responses 1.csv"));
		}
		catch(FileNotFoundException e) {																		//If file fails is not found
			outFile.println("Input file not found...");															//Print problem to error log
			e.printStackTrace(outFile);																			//Print stack trace to error log
			outFile.println();
			outFile.close();																					//Close error log since there are no more errors to catch
			return false;																						//Function run failed, returned false
		}
		
		String firstName, lastName, UIN, ignore, temp; int javaKnowledge = -1, section = -1, rank = -1;			//Creating variables where file input will be stored
		
		inFile.nextLine();																						//Skip first line of file (Entry data)
		
		int lineNum = 1;																						//lineNum is the ENTRY, not the LINE
		while(inFile.hasNextLine()) {																			//Loop until there is no more lines left to read
			String line = inFile.nextLine();																	//line holds data for one line at a time
			StringTokenizer tokenizer = new StringTokenizer(line, ",");											//tokenizer will hold an individual element from the file
			
			try {																							//Timestamp:
				ignore = tokenizer.nextToken();																	//timestamp is ignored
			}
			catch(NoSuchElementException e) {																	//If timestamp entry does not exist
				outFile.println("Entry " + lineNum + ": Missing timestamp... Entry skipped");					//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			try {																							//Java knowledge:
				javaKnowledge = Integer.parseInt(tokenizer.nextToken());										//javaKnowledge holds and int value
			}
			catch(NoSuchElementException e) {																	//If javaKnowledge entry does not exist
				outFile.println("Entry " + lineNum + ": Missing java knowledge... Entry skipped...");			//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			catch(NumberFormatException e) {																	//If javaKnowledge is not an int
				outFile.println("Entry " + lineNum + ": Invalid java knowledge type... Entry skipped...");		//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();																				
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			finally {																							//If javaKnowledge passes tests
				if(javaKnowledge > 5 || javaKnowledge < 1) {													//If javaKNowledge not within valid range [1,5]
					outFile.println("Entry " + lineNum + ": Invalid java knowledge range... Entry skipped...");	//Print problem to error log
					outFile.println("(Utility.java)");															//Print problem location to error log
					outFile.println();
					lineNum++;																					//Since entry is not longer valid, next line
					continue;																					//Skip the rest of the entry
				}
			}
			try {																							//Java history
				ignore = tokenizer.nextToken();																	//Java history is ignored	
			}
			catch(NoSuchElementException e) {																	//If java history does not exist
				outFile.println("Entry " + lineNum + ": Missing java history... Entry skipped...");				//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();			
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			try {																							//Partner Preference
				ignore = tokenizer.nextToken();																	//Partner preference is ignored
				if(ignore.equals("\"No"))																		//This cell may contain an extra comma
					ignore = tokenizer.nextToken();																//If it does, skip over it
			}
			catch (NoSuchElementException e) {																	//If partner preference does not exist
				outFile.println("Entry " + lineNum + ": Missing partner preference... Entry skipped...");		//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			try {																							//Name
				temp = tokenizer.nextToken();																	//temp holds value of first and last name here
			}
			catch (NoSuchElementException e) {																	//If Name does not exist
				outFile.println("Entry " + lineNum + ": Missing first and last name... Entry skipped...");		//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			try {
				firstName = temp.substring(0, temp.indexOf(" "));												//firstName is the value in temp before the space
				lastName = temp.substring(temp.indexOf(" ") + 1);												//lastName is the value in temp after the space
			}
			catch (StringIndexOutOfBoundsException e) {															//If space does not exist
				outFile.println("Entry " + lineNum + ": Invalid- first and last name not separated by space... Entry skipped..."); //Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			try {																							//Section
				temp = tokenizer.nextToken();																	//temp holds the int value of section and section time
				section = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));								//section is assigned to just the int value of section
			}
			catch (NoSuchElementException e) {																	//If section does not exist
				outFile.println("Entry " + lineNum + ": Missing section... Entry skipped...");					//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();	
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			catch (StringIndexOutOfBoundsException e) {															//If section does not contain both elements (time, section)
				outFile.println("Entry " + lineNum + ": Invalid section entry... Entry skipped...");			//Print problem to error log							
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();	
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			catch (NumberFormatException e) {																	//If section number is not of type int
				outFile.println("Entry " + lineNum + ": Invalid section type... Entry skipped...");				//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			finally {																							//If section passes tests
				if(section < 0) {																				//If section number not within valid range >0
					outFile.println("Entry " + lineNum + ": Invalid section range... Entry skipped...");		//Print problem to error log
					outFile.println("(Utility.java)");															//Print problem location to error log
					outFile.println();
					lineNum++;																					//Since entry is not valid, next line
					continue;																					//Skip the rest of the entry
				}
			}
			try {																							//Email/UIN
				temp = tokenizer.nextToken();																	//temp holds the whole email address
				UIN = temp.substring(0, temp.indexOf("@"));														//UIN is just the email before the "@"
			}
			catch (NoSuchElementException e) {																	//If email does not exist
				outFile.println("Entry " + lineNum + ": Missing email... Entry skipped...");					//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();					
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			catch (StringIndexOutOfBoundsException e) {															//If email has no @ sign
				outFile.println("Entry " + lineNum + ": Invalid- email missing \"@\"... Entry skipped...");		//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();																				
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			try {																							//Rank
				rank = Integer.parseInt(tokenizer.nextToken());													//rank holds an int value
			}
			catch (NoSuchElementException e) {																	//If rank does not exist
				outFile.println("Entry " + lineNum + ": Missing rank... Entry skipped...");						//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();	
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}			
			catch (NumberFormatException e) {																	//If rank is not an int
				outFile.println("Entry " + lineNum + ": Invalid rank type... Entry skipped...");				//Print problem to error log
				e.printStackTrace(outFile);																		//Print stack trace to error log
				outFile.println();
				lineNum++;																						//Since entry is not valid, next line
				continue;																						//Skip the rest of the entry
			}
			finally {																							//If rank passes tests
				if(rank > 4 || rank < 1) {																		//If rank not within valid range [1, 4]
					outFile.println("Entry " + lineNum + ": Invalid rank range... Entry skipped...");			//Print  problem to error log
					outFile.println("(Utility.java)");															//Print problem location to error log
					outFile.println();		
					lineNum++;																					//Since entry is not valid, next line
					continue;																					//Skip the rest of the entry
				}
			}
			CPSC314Student aStudent = new CPSC314Student(firstName, lastName, UIN, javaKnowledge, section, rank); //If entry is 100% valid, make new 314 student
			StudentList.add(aStudent);																			//Add this student to full list of valid students
			lineNum++;																							//Once student is created and added to list, next line
			
			if(debug) {																							//Console prints to check different values when debugging
				System.out.println("Name : " + firstName + " " + lastName);
				System.out.println("UIN : " + UIN);
				System.out.println("Java Knowledge : " + javaKnowledge);
				System.out.println("Section : " + section);
				System.out.println("Classification : " + rank);
			}
		}
		inFile.close();
		outFile.close();
		return true;																							//After loop finishes, file input was succeeded, return true
	}
	boolean writeResults(ArrayList<ArrayList<CPSC314Student>> studentSections, ArrayList<Integer> Sections) {
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter("results.txt");															//Try to open new output file with given name
		}
		catch(FileNotFoundException e) {																		//If file is not found
			System.out.println("File not found");																//Print to CONSOLE since error log file cannot be opened
			e.printStackTrace();																				//Print to CONSOLE stack trace
			return false;																						//Function run failed, returned false
		}
		
		for(int i = 0; i < studentSections.size(); i++) {														//Loops through each section
			outFile.println("				SECTION " + Sections.get(i));										//Prints current section in format
			outFile.println();
			int f = studentSections.get(i).size() - 1;															//f will go through loop backwards (higher javaKnowledge)
			for(int s = 0; s < studentSections.get(i).size()/2 + 1; s ++) {										//s will go through loop forwards (lower javaKnowledge)
				outFile.print(Sections.get(i) + " - ");															//Print section of both student on line
				if(s == f) {																					//If s and f are the same
					outFile.print("(Sub) " + studentSections.get(i).get(s).toPrint());							//Odd number of students. This one is a sub and printed
					outFile.println();
					break;																						//Break out of loop
				}
				else 																							//If s and f are not the same
					outFile.print(studentSections.get(i).get(s).toPrint() + " " + studentSections.get(i).get(f).toPrint());	//Students are matched and printed
				outFile.println();
				f--;																							
			}
			if(i != Sections.size() - 1) {																		//Simple formatting for ease of reading
				outFile.println();
				outFile.println();
				outFile.println();
				outFile.println();
			}
		}
		outFile.close();
		return true;																							//Function run succeeded, return true
	}	
	
}
