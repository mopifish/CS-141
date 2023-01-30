// 1.30.23
// Drake Picket
// Example of Methods
/* A method is a very handy way to re-use code.
   It makes your projects smaller and much more manageable */

import java.util.Scanner;

public class MethodExample{
	// Main is actually an example of a method. 
	public static void main(String[] args){
		// Program without methods
		System.out.println("Welcome to....");
		System.out.println("HANDY");
		System.out.println("\tDANDY");
		System.out.println("\t\tMETHOD EXAMPLE!");

		Scanner input = new Scanner(System.in);

		System.out.print("> ");
		userNumber = input.nextInt();	

		System.out.println(userNumber/userNumber*userNumber)

		// Program with methods
		printStartingMessage();
		int userInput = getUserInput();
		System.out.println(divideThenMultiply(userInput));

		// Using methods properly can make your code easier to read
		// Using proper function names is called "self-commenting" code
		// It's when the code explains itself!


	}// End of main


	// Methods can be used to label and break up large chunks of code

	// Prints start up message for user
	public static void printStartingMessage(){
		System.out.println("Welcome to....");
		System.out.println("HANDY");
		System.out.println("\tDANDY");
		System.out.println("\t\tMETHOD EXAMPLE!");
	}

	// Methods can also be used to avoid repeating lines of code
	// Methods can return values too. What value it returns
	// Is indicated by the keyword before the method name. 
	// Void indicates no return type, while int indicates an integer return type

	// Gets user input
	public static int getUserInput(){
		Scanner input = new Scanner(System.in);

		System.out.print("> ");
		userNumber = input.nextInt();	
	}


	// Finally, methods can be given "parameters", or arguments

	public static int divideThenMultiply(int num){
		return num / num * num;
	}

}//End ofClass

