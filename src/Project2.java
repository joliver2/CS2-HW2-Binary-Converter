/* Author: Jeremy Oliver
 * Date: 1-23-2018
 * 
 * Description: This program converts one-byte binary numbers to decimal numbers.
 * One method takes user inputs and displays the results to the console. Another method 
 * takes inputs from a .txt file and prints the results to an output .txt file.

Limitations: This program assumes binary numbers are in standard form, unsigned, with base 2.
	         Only reads inputs line by line from a text file.
	         All inputs must be 8 digits long or the result will return as invalid.
	         Only calculates binary numbers from 0 to 255
*/
import java.io.*;
import java.util.*;
	
public class Project2 {
	
	// creates scanner class for keyboard
	Scanner keyboard = new Scanner(System.in);
	
	// Creates array list to store user inputs
	private ArrayList<String> binaryNumbers = new ArrayList<String>(25);
	
	private String isValid;   // String to say weather number is valid or invalid
	private int decimalValue; // Holds decimal value of binary numbers


	// Method that prints a message to the screen explaining the program
	private void printInfo() {
		System.out.println("Welcome to the Binary Converter!"
		 + "\n\nThis program will take 8-digit binary inputs from a file or user\n"
		 + "and check whether or not that binary number is a valid 8-bit \n"
		 + "decimal number. The result will be output to the screen or output file\n");
	}
	
	/* Method that prompts the user for strings to be evaluated; asks the 
	 * user how many values they have and loops until all are evaluated; 
	 * calls validateNumber and, on valid numbers, calls evaluateNumber*/
	private void processUserNumbers() {	
		
		int totalNumbers;   // Holds the number of inputs that user enters
		
		// Asks user how many binary numbers they want to evaluate
		System.out.print("How many binary numbers do you want to evaluate?\n>>");
		totalNumbers = keyboard.nextInt();
		
		// Ask user to input binary numbers. Loops equivalent to the answer from previous question
		System.out.println("Enter your binary numbers (one binary number at a time. Hit the return "
		+ "key after each entry): ");
		
		// Stores user inputs into an array list
		for (int i = 0; i <= totalNumbers; i++) {
			binaryNumbers.add(keyboard.nextLine());
		} //End ask user method
		
		/* runs through the array list of inputs, checks the validity of the numbers, calculates the
		decimal result if the number is valid. Prints the results to the screen*/
		for (int i=1; i <= totalNumbers; i++) {	
			if (validateNumber(binaryNumbers.get(i)) == true)  {
				isValid = "valid";
				decimalValue = evaluateNumber(binaryNumbers.get(i));
		    System.out.println("\nstring: " + binaryNumbers.get(i) + "\nstatus: " + isValid 
		    		+ "\ndecimal value: " + decimalValue);
			} else {
				isValid = "Invalid";	
			System.out.println("\nstring: " + binaryNumbers.get(i) + "\nstatus: " + isValid);
			}
		
		} // end loop
		
	} // end calculate and print method
		
	/* Method that checks a string to see if it is length eight and contains 
	 * only valid binary digits (zero and one); returns a boolean*/
	// I KNOW THIS IS UGLY! IF TIME PERMITS I WILL RE-WORK THIS METHOD
	public boolean validateNumber(String userNumber) {
	  return ((userNumber.length() == 8) &&
				   (userNumber.charAt(7) == '0' || userNumber.charAt(7) == '1') && 
				   (userNumber.charAt(6) == '0' || userNumber.charAt(6) == '1') &&
				   (userNumber.charAt(5) == '0' || userNumber.charAt(5) == '1') &&
				   (userNumber.charAt(4) == '0' || userNumber.charAt(4) == '1') &&
				   (userNumber.charAt(3) == '0' || userNumber.charAt(3) == '1') &&
				   (userNumber.charAt(2) == '0' || userNumber.charAt(2) == '1') &&
				   (userNumber.charAt(1) == '0' || userNumber.charAt(1) == '1') &&
				   (userNumber.charAt(0) == '0' || userNumber.charAt(0) == '1'));
	} // end validateNumber
	
	/*takes a validated number and calculates its decimal value; assumes the 
	 * binary number is unsigned*/
	public int evaluateNumber(String validatedNumber) {
		int total = 0;
		
		for (int i = 0; i < validatedNumber.length(); i++) {
			char a = validatedNumber.charAt(i);
			if (a == '1') {
		    total += Math.pow(2, i);
			} 
		} return total;
	} // End evaluate number
	
	/* Method similar to processUserNumbers but reads from an input file and 
	 * outputs to a report file*/
	public void processFileNumbers() {
	
	try {
		// Access input and output files
		FileReader inputFile = new FileReader("P2Input.txt");
		BufferedReader bufferedReader = new BufferedReader(inputFile);
	     
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter("P2Output.txt"));
		
		// Create new scanner from input file
		Scanner input = new Scanner(new File("P2Input.txt"));
		
		int lineCount = 0;   // Variable to count 
		
		// Prints out a header on the ouput file
	    outputWriter.write(" STRING    STATUS   DECIMAL VALUE   \n"
	    		+ "___________________________________\n");
		
		
	    // Fills an array with values from the input file
		while (input.hasNextLine()) {
			  binaryNumbers.add(input.nextLine());
			  lineCount++;
			}
    		
    		// Runs through the array list of inputs, checks the validity of the numbers, calculates the
    		// decimal result if the number is valid. Prints the results to an output file.
    		
    		for (int i=1; i < lineCount; i++) {	
    			
    			if (validateNumber(binaryNumbers.get(i)) == true)  {
    				isValid = "valid";
    				decimalValue = evaluateNumber(binaryNumbers.get(i));
    		    outputWriter.write(binaryNumbers.get(i) + "   " + isValid 
    		    		+ "       " + decimalValue + "\n");
    			} else {
    				isValid = "Invalid";	
    			outputWriter.write(binaryNumbers.get(i) + "   " + isValid 
    		    		+ "       \n");
    			} // end else
  
    		} // end for loo[
    		
    		// Always close your files!
    		inputFile.close();
		outputWriter.close();
}
	catch (FileNotFoundException ex) {
		System.out.println( "Unable to open file");                
}
	catch (IOException ex) {
		System.out.println("Error reading file");                  

}
	} // end process file numbers
	
	/* Main method calls printInfo and one of the process methods*/
	public static void main(String[] args) throws IOException{
		Project2 binaryInfo = new Project2();
	    binaryInfo.printInfo();
	    
    // This method is to run this program from the console based on user input
    // rather than input from a file.
	   Project2 processInput = new Project2();
	   processInput.processUserNumbers();
	
   //  This method runs the program from an input file and exports to an output file
	   processInput.processFileNumbers();
	    
	 // Gotta close the system   
	 System.exit(0);
	}	
}
