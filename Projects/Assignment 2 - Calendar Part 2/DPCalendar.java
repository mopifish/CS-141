// Drake Pickett
// 2.14.23
// CS &141
// Assignment #2 - Calendar Part 2
/* This programs takes user input to display a given month on a calendar,
   then prints the current month on the calendar. 						

   This project took me aproximately 3-4 hours										*/

import java.util.Scanner;
import java.util.Calendar;


public class DPCalendar{	

	// --- Main Function ---
	public static void main(String[] args){
		// -- Constants
		final String VALID_COMMANDS = "etnpq";			// List of character commands
		final String calendarPicture = "┌───────────────────────────────────────────────┐\n│                                    ===========│\n│                                    ===========│\n│                                      =========│\n│                 #######              =========│\n│                ##     ###            =========│\n│               ##@ v @   ##            ========│\n│       =       #     ###  #            ========│\n│       ==      #      ## ###           ========│\n│        ====   ###      #####     =============│\n│           ==    ########    ==================│\n│          ========^===^========================│\n│                       ========================│\n│    bird             ===              =========│\n│                                        =======│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n└───────────────────────────────────────────────┘";

		// -- Global variables
		Calendar cal = Calendar.getInstance();
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);
		int currentMonth = cal.get(Calendar.MONTH) +1;

		char userCommand;
		boolean quit = false;

		int calendarMonth = 0;		// Default initialization value
		int calendarDay = 0;			// Default initialization value

		// -- Main loop
		do{
			System.out.println("Please type a command");
			printCommands();
			userCommand = getUserCommand(VALID_COMMANDS);

			switch (userCommand){
				case 'e':
					String userDate = getUserDate();

					calendarMonth = getMonthFromDate(userDate);
					calendarDay = getDayFromDate(userDate);

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
		printDate(month, day);
	}
	public static void drawMonth(int month, int day){ 
		System.out.println("\t\t\t" + month); 			// Display month number
		System.out.println("  Sun    Mon    Tue    Wed    Thu    Fri    Sat  "); 		// Print days of week
		for (int i = 0; i < 5; i++){
			drawWeek(i, month, day);
		}
	}
	public static void drawWeek(int row, int month, int day){
		String unmarkedCell = "=======|  %3d||     |======="; 				// ASCII Cell art stored in a string, 4 even segments
		String markedCell = "=======|xx%3d||xxxxx|======="; 					// ASCII Cell art stored in a string, 4 even segments
		String emptyCell = "=======|     ||     |======="; 					// ASCII Cell art stored in a string, 4 even segments
		int cellSize = unmarkedCell.length()/4; 									// Gets size of 1 calendar cell

		int firstDayOfMonth = getDayOfWeek(month, 1) - 1;

		for (int i = 0; i < unmarkedCell.length(); i += cellSize){ 			// For each row in a cell
			for (int j = row*7; j < (row*7)+7; j++){ 								// For each day in a week
				// Initial Declarations
				int dayOfMonth = j+1 - firstDayOfMonth; 

				// Get cell type (marked, unmarked, or empty)
				String cell = unmarkedCell;
				if (j+1 - firstDayOfMonth == day) { 
					cell = markedCell; 
				} else if (dayOfMonth > getLengthOfMonth(month) || dayOfMonth < 1) { 
					cell = emptyCell;
				}
				
				String cellRow = cell.substring(i, i+cellSize); 

				// Print cell row
				if (cellRow.indexOf("%3d") != -1){			// If row displays a number date, printf, else print normal
					System.out.printf(cellRow, dayOfMonth);
				}else{ 
					System.out.print(cellRow); 
				}
				
			}
			System.out.print("\n"); // End current row
		}
	}


	// --- Calendar Math Functions ---
	public static int getDayOfYear(int month, int day){
		int dayOfYear = day;
		for (int i = month-1; i > 0; i--){
			dayOfYear += getLengthOfMonth(i);
		}

		return dayOfYear;
	}
	public static int getLengthOfMonth(int month){
		// Bastardizes a string and treats it like a messy array to store month lengths
		String daysPerMonth = "31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31";
		return Integer.parseInt(daysPerMonth.substring((month-1)*4, (month-1)*4 + 2));
	}
	public static int getDayOfWeek(int month, int day){
		int dayOfYear = getDayOfYear(month, day);
		int firstDayOfYear = 1; // Day of week on first day of the year, 0-6, Sun-Sat
		return ((dayOfYear%7 + firstDayOfYear == 0 ? dayOfYear%7 + 7 + firstDayOfYear : dayOfYear%7 + firstDayOfYear)); // If day is a saturday, % returns 0 instead of 7. This fixes that. 
	}
	public static int getMonthFromDate(String date){
		return Integer.parseInt(date.substring(0, date.indexOf("/"))); // Substrs mm/dd from first letter up to slash (non-inclusive)
	}
	public static int getDayFromDate(String date){
		return Integer.parseInt(date.substring(date.indexOf("/")+1)); // Substrs mm/dd from letter after slash to end
	}


	// --- Print Functions ---
	public static void printCommands(){
		System.out.println("\t\"e\" to enter a date and display a corresponding calendar");
		System.out.println("\t\"t\" to get today's date and display today's calendar");
		System.out.println("\t\"n\" to display the next month");
		System.out.println("\t\"p\" to display the previous month");
		System.out.println("\t\"q\" to quit the program");
	}
	public static void printDate(int month, int day){
		System.out.println("Month: " + month);
		System.out.println("Day: " + day);
	}

	// --- Terminal Functions --- 
	public static void clearOutput(){
	// "Clears" current terminal by printing 20 empty lines
		for (int i = 0; i < 20; i++){
			System.out.println();
		}
	}
	
}