//Haena Song, CIS 340, 1:30 TTH, MP2 
import java.util.Scanner; 

public class Utilities {
	Scanner scan = new Scanner(System.in); 
	
	public int readInteger(String displayString) {
		int number = 0;
		boolean repeatInput = false;
		do {
			try {
				System.out.printf("%s", displayString); //display the argument 
				number = (Integer.parseInt(scan.nextLine())); //read an integer and assign in to number 
				repeatInput = false; //no error; end the try 
				break;
			}
			catch (Exception ex) {
				System.out.println("\nInput must be a valid integer. Try again.");
				repeatInput = true;
			}
		} while (repeatInput = true);
		return number;
	}
	
	public void invalidNumber() {
		System.out.println("Enter a valid number.");
	}
	

}
