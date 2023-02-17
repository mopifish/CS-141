// Drake Pickett
// 2.14.23
// CS &141
// Assignment #2 - Calendar Part 2
/* This programs takes user input to display a given month on a calendar,
   then prints the current month on the calendar. 						

   This project took me aproximately XXXXX hours										*/

import java.util.Scanner;
import java.util.Calendar;


public class DPCalendar{	

	// --- Main Function ---
	public static void main(String[] args){
		// -- Constants
		final String VALID_COMMANDS = "etnpq"; // List of character commands
		final String calendarPicture = "┌───────────────────────────────────────────────┐\n│                                    ===========│\n│                                    ===========│\n│                                      =========│\n│                 #######              =========│\n│                ##     ###            =========│\n│               ##@ v @   ##            ========│\n│       =       #     ###  #            ========│\n│       ==      #      ## ###           ========│\n│        ====   ###      #####     =============│\n│           ==    ########    ==================│\n│          ========^===^========================│\n│                       ========================│\n│    bird             ===              =========│\n│                                        =======│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n└───────────────────────────────────────────────┘";

		// -- Global variables
		Calendar cal = Calendar.getInstance();
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);
		int currentMonth = cal.get(Calendar.MONTH) +1;
		
		char userCommand;
		boolean quit = false;

		int calendarMonth = 0; // Default initialization value
		int calendarDay = 0; // Default initialization value

		// -- Main loop
		do{
			System.out.println("Please type a command");
			printCommands();
			userCommand = getUserCommand(VALID_COMMANDS);

			switch (userCommand){
				case 'e':
					String userDate = getUserDate();

					calendarMonth = monthFromDate(userDate);
					calendarDay = dayFromDate(userDate);

					drawCalendar(calendarMonth, calendarDay, calendarPicture);

					break;
				case 't':
					calendarMonth = currentMonth;
					calendarDay = currentDay;
					drawCalendar(currentMonth, currentDay, calendarPicture);
					break;
				case 'n':
					if (calendarMonth != 0){
						calendarMonth++;
						if (calendarMonth > 12){calendarMonth = 1;}
						drawCalendar(calendarMonth, calendarDay, calendarPicture);
					} else {
						System.out.println("You must display a calendar before you can change the month");
					}
					
					break;
				case 'p':
					if (calendarMonth != 0){
						calendarMonth--;
						if (calendarMonth < 1){calendarMonth = 12;}
						drawCalendar(calendarMonth, calendarDay, calendarPicture);
					} else {
						System.out.println("You must display a calendar before you can change the month");
					}
					break;
				case 'q':
					quit = true;
					break;
			}

		} while (quit != true);
	}

	// --- Input Functions
	public static char getUserCommand(String COMMANDS){
		Scanner input = new Scanner(System.in);
		
		char userCommand;
		do{
			System.out.print("> ");
			userCommand = input.next().toLowerCase().charAt(0);
			System.out.print("\n");
		} while (COMMANDS.indexOf(userCommand) == -1);

		return userCommand;
	}
	public static String getUserDate(){
		Scanner input = new Scanner(System.in);

		String date;
		System.out.print("Please enter a date (mm/dd): ");
		date = input.next();

		return date;
	}

	// --- Calendar Drawing Functions ---
	public static void drawCalendar(int month, int day, String calendarPicture){
		clearOutput();	
		System.out.println(calendarPicture);
		drawMonth(month, day);
		displayDate(month, day);
	}
	public static void drawMonth(int month, int day){ 
		System.out.println("\t\t\t" + month); // Display month number
		for (int i = 0; i < 5; i++){
			drawRow(i, day);
		}
	}
	public static void drawRow(int row, int day){
		String unmarkedCell = "=======|  %3d||     |======="; // ASCII Cell art stored in a string, 4 even segments
		String markedCell = "=======|xx%3d||xxxxx|======="; // ASCII Cell art stored in a string, 4 even segments
		int cellSize = unmarkedCell.length()/4; // Gets size of 1 calendar cell

		for (int i = 0; i < unmarkedCell.length(); i += cellSize){ // For each row in a cell
			for (int j = row*7; j < (row*7)+7; j++){ // For each day in a week
				String cell = (j+1 == day ? markedCell : unmarkedCell);
				String cellRow = cell.substring(i, i+cellSize); // Get current cell row

				if (cellRow.indexOf("%3d") != -1){ // If row displays a number date, printf, else print normal
					if (j >= 31){ // Reset week count at day 31
						System.out.printf(cellRow, (j + 1) - 31);
					} else {
						System.out.printf(cellRow, j + 1);
					}
				}else{ 
					System.out.print(cellRow); 
				}
				
			}
			System.out.print("\n"); // End current row
		}
	}

	//--- Math Functions ---
	public static int monthFromDate(String date){
		return Integer.parseInt(date.substring(0, date.indexOf("/"))); // Substrs mm/dd from first letter up to slash (non-inclusive)
	}
	public static int dayFromDate(String date){
		return Integer.parseInt(date.substring(date.indexOf("/")+1)); // Substrs mm/dd from letter after slash to end
	}

	// --- Display Functions ---
	public static void displayDate(int month, int day){
		System.out.println("Month: " + month);
		System.out.println("Day: " + day);
	}

	// --- Print Functions ---
	public static void printCommands(){
		System.out.println("\t\"e\" to enter a date and display a corresponding calendar");
		System.out.println("\t\"t\" to get today's date and display today's calendar");
		System.out.println("\t\"n\" to display the next month");
		System.out.println("\t\"p\" to display the previous month");
		System.out.println("\t\"q\" to quit the program");
	}

	// --- Terminal Functions --- 
	public static void clearOutput(){
	// "Clears" current terminal by printing 20 empty lines
		for (int i = 0; i < 20; i++){
			System.out.println();
		}
	}
}