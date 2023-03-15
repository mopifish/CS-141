// Drake Pickett
// 3.14.23
// CS &141
// Assignment #3 - Calendar Part 3
/* This programs takes user input to display a given month on a calendar,
   then prints the current month on the calendar. 						

   This project took me aproximately 									*/


/*
TODO:
- Event Scheduling
	- Take in Events
	- Store Events
- Event File Reading
	- Read event file
	- Store events
- File Printing
	- Print calendar to file
*/


import java.util.Scanner;
import java.util.Calendar;
import java.io.*;

public class DPCalendar{	
	// --- Main Function ---
	public static void main(String[] args){
		// -- Global variables
		String userCommand;
		boolean quit = false;

		// Initiates calendar on current date
		Calendar javaCal = Calendar.getInstance();
		ConsoleCalendar calendar = new ConsoleCalendar((javaCal.get(Calendar.MONTH) +1), javaCal.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar);
		

		// -- Main loop
		do{
			System.out.println("Please type a command");
			printCommands();
			userCommand = getUserCommand();

			switch (userCommand){
				case "e":
					calendar.setDate(getUserDate());
					System.out.println(calendar);

					break;
				case "t":
					calendar.setDate((javaCal.get(Calendar.MONTH) +1) + "/" + javaCal.get(Calendar.DAY_OF_MONTH));
					System.out.println(calendar);
					break;
				case "n":
					calendar.setMonth(calendar.getMonth()+1);
					System.out.println(calendar);
					
					break;
				case "p":
					calendar.setMonth(calendar.getMonth()-1);
					System.out.println(calendar);

					break;
				case "ev":
					System.out.println("You EV'd!");
					break;
				case "ef":
					try {
						PrintStream exportFile = new PrintStream(new File("Exports\\" + getUserInput("Export File Name: ")));
						calendar.setDate(getUserDate());
						exportFile.println(calendar);
					} catch (FileNotFoundException e){
						System.out.println("ERROR: File not found");
					}
					
					break;
				case "q":
					quit = true;
					break;
			}

		} while (quit != true);
	}


	// --- Input Functions
	public static String getUserCommand(){
		String COMMANDS = "e t n p q ev ef";
		Scanner input = new Scanner(System.in);
		
		String userCommand;
		do{
			System.out.print("> ");
			userCommand = input.next().toLowerCase();
			System.out.print("\n");
		} while (COMMANDS.contains(userCommand) != true);

		return userCommand;
	}
	public static String getUserDate(){
		Scanner input = new Scanner(System.in);

		String date;
		System.out.print("Please enter a date (mm/dd): ");
		date = input.next();

		return date;
	}	
	public static String getUserInput(String prompt){
		Scanner input = new Scanner(System.in);

		System.out.print(prompt);
		return input.nextLine();
	}

	// --- Print Functions ---
	public static void printCommands(){
		System.out.println("\t\"e\" to enter a date and display a corresponding calendar");
		System.out.println("\t\"t\" to get today's date and display today's calendar");
		System.out.println("\t\"n\" to display the next month");
		System.out.println("\t\"p\" to display the previous month");
		System.out.println("\t\"ev\" to enter a new event");
		System.out.println("\t\"ef\" to export a month to a file");
		System.out.println("\t\"q\" to quit the program");
	}


}