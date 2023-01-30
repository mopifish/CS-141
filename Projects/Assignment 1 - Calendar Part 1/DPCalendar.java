// Drake Pickett
// 1.24.23
// CS &141
// Assignment #1 - Calendar Part 1
/* This programs takes user input to display a given month on a calendar,
   then prints the current month on the calendar. 						

   This project took me aproximately 2 hours										*/

import java.util.Scanner;
import java.util.Calendar;

public class DPCalendar{
	//-------Main Function--------
	public static void main(String[] args){
		// -- Initial Declarations
		Scanner input = new Scanner(System.in); // Declare input
		Calendar cal = Calendar.getInstance();
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);
		int currentMonth = cal.get(Calendar.MONTH) +1;
		String calendarPicture = "┌───────────────────────────────────────────────┐\n│                                    ===========│\n│                                    ===========│\n│                                      =========│\n│                 #######              =========│\n│                ##     ###            =========│\n│               ##@ v @   ##            ========│\n│       =       #     ###  #            ========│\n│       ==      #      ## ###           ========│\n│        ====   ###      #####     =============│\n│           ==    ########    ==================│\n│          ========^===^========================│\n│                       ========================│\n│    bird             ===              =========│\n│                                        =======│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n└───────────────────────────────────────────────┘";

		// -- Startup Message
		clearOutput(); // Clear current screen (for visual consistency)
		System.out.println("Welcome to the 2023-2024 Un-Official Bird Calendar!");
		
		// -- Fetch and parse user input
		System.out.println("What date would you like to look at? (mm/dd)");
		String userInput = input.next();
		System.out.print("\n");

		int inputMonth = monthFromDate(userInput);
		int inputDay = dayFromDate(userInput);

		// -- Print month from user input
		clearOutput();	
		System.out.println(calendarPicture);
		drawMonth(inputMonth);
		displayDate(inputMonth, inputDay);
		pauseOutput("\n\nPress enter to continue...");

		// -- Print current month from calendar
		clearOutput();
		System.out.println("This Month: ");
		System.out.println(calendarPicture);
		drawMonth(currentMonth);
		displayDate(currentMonth, currentDay); // Display date using current month and current day
		pauseOutput("\n\nPress enter to quit");
	}

	//------Draw functions------
	public static void drawMonth(int month){ 
	// Draw Month Function; Draws 5 calendar rows
		System.out.println("\t\t\t" + month); // Display month number
		for (int i = 0; i < 5; i++){
			drawRow(i);
		}
	}
	public static void drawRow(int row){
	// Draw Row Function; Draws one calendar week
		String cell = "=======|  %3d||     |======="; // ASCII Cell art stored in a string, 4 even segments
		int cellSize = cell.length()/4; // Gets size of 1 calendar cell

		for (int i = 0; i < cell.length(); i += cellSize){ // For each row in a cell
			for (int j = row*7; j < (row*7)+7; j++){ // For each day in a week
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

	//------Math Functions------
	public static int monthFromDate(String date){
		return Integer.parseInt(date.substring(0, date.indexOf("/"))); // Substrs mm/dd from first letter up to slash (non-inclusive)
	}
	public static int dayFromDate(String date){
		return Integer.parseInt(date.substring(date.indexOf("/")+1)); // Substrs mm/dd from letter after slash to end
	}

	//-------Display Functions------
	public static void displayDate(int month, int day){
		System.out.println("Month: " + month);
		System.out.println("Day: " + day);
	}

	//------Output Managing Functions------
	public static void pauseOutput(String message){
	// Pauses output until Enter key is pressed, utilizes Scanner
		Scanner input = new Scanner(System.in);

		System.out.print(message);
		input.nextLine();
	}
	public static void clearOutput(){
	// "Clears" current terminal by printing 20 empty lines
		for (int i = 0; i < 20; i++){
			System.out.println();
		}
	}


}