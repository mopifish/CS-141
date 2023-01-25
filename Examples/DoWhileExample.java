// 1.25.2023
// Drake Pickett
// Example of do/while syntax
/* A do/while loop will always perform a check at the end of each loop
	Guarantees the body will run at least once */

import java.util.Scanner;

public class DoWhileExample{
	public static void main(String[] args){
		// Awesome for input validation!
		Scanner input = new Scanner(System.in);

		int userNumber;
		do { // initial statement will always happen at least once
			System.out.print("Please enter a positive number: ");
			userNumber = input.nextInt();

		} while (userNumber <= 0); // If user enters a non-positive number, the loop will repeat!

		// It can also be used to run continuous programs
		int userInput;
		do {

			System.out.println("Your number is: " + userNumber);
			System.out.println("Enter 1 to Increase your number \n2 to Decrease \nOr -1 to Quit");
			userInput = input.nextInt();

			switch (userInput){
				case 1:
					userNumber++;
					break;
				case 2:
					userNumber--;
					break;
			}

		} while (userInput != -1);
	}

}