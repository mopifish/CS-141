/* Console Calendar Class
	Calendar object that stores dates and events. 
	Can be printed
*/

import java.util.Calendar;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class ConsoleCalendar {
	
	private static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private static final int[] DAYS_PER_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	private String[][] eventsList = new String[12][];
	private String picture = "┌───────────────────────────────────────────────┐\n│                                    ===========│\n│                                    ===========│\n│                                      =========│\n│                 #######              =========│\n│                ##     ###            =========│\n│               ##@ v @   ##            ========│\n│       =       #     ###  #            ========│\n│       ==      #      ## ###           ========│\n│        ====   ###      #####     =============│\n│           ==    ########    ==================│\n│          ========^===^========================│\n│                       ========================│\n│    bird             ===              =========│\n│                                        =======│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n└───────────────────────────────────────────────┘\n";
	private Date date;

	// --- Constructor ---
	public ConsoleCalendar(int month, int day){
		// Calendar initializes on current day/month
		Calendar cal = Calendar.getInstance();
		this.date = new Date(month + "/" + day);
		initializeEventsList();
	}

	// --- Init functions ---
	public void initializeEventsList(){
		// Initializes Array size
		for (int i = 0; i < eventsList.length; i++){
			eventsList[i] = new String[getLengthOfMonth(i+1)];
		}

		File file;

		file = new File("calendarEvents.txt");
		try{
			Scanner fileReader = new Scanner(file);

			while (fileReader.hasNextLine()){

				String eventLine = fileReader.nextLine();
				System.out.println(eventLine);
				Date eventDate = new Date(eventLine.substring(0, eventLine.indexOf(" ")));
				String event = eventLine.substring(eventLine.indexOf(" ")+1, eventLine.length()).replace("_", " ");
				eventsList[eventDate.getMonth()-1][eventDate.getDay()-1] = event;
			}

		} catch (FileNotFoundException e){
			System.out.println("No Events Found");
		}

	}

	// --- Setget Functions ---
	public void addEvent(Date eventDate, String eventName){
		eventsList[eventDate.getMonth()-1][eventDate.getDay()-1] = eventName;
	}

	public String[][] getEventsList(){
		return eventsList;
	}

	public void setDate(String newDate){
		date.setDate(newDate);
	}
	public Date getDate(){
		return date;
	}

	public void setMonth(int month){
		// Wraps given date between 1 and 12
		if (month > 12){
			month = 1;
		}else if (month < 1){
			month = 12;
		}

		getDate().setMonth(month);
	}
	public int getMonth(){
		return getDate().getMonth();
	}

	public void setDay(int day){
		getDate().setDay(day);
	}
	public int getDay(){
		return getDate().getDay();
	}


	// --- To String Functions ---
	public String toString(){
		return picture + monthToString() + getDate().toString();
	}
	public String monthToString(){ 
		String output = "                                      " + MONTH_NAMES[getMonth()-1] + "\n\n";
		output +=       "     Sun         Mon         Tue         Wed         Thu         Fri         Sat  \n";
		for (int i = 0; i < 5; i++){  
			output += weekToString(i);
		}

		return output;
	}
	public String weekToString(int row){
		String output = "";

		String unmarkedCell = "============|       %3d||          ||          ||          |============"; 				// ASCII Cell art stored in a string, 4 even segments
		String eventCell =    "============|       %3d||1111111111||2222222222||3333333333|============"; 				// ASCII Cell art stored in a string, 4 even segments
		String markedCell =   "============|xxxxxxx%3d||xxxxxxxxxx||xxxxxxxxxx||xxxxxxxxxx|============"; 					// ASCII Cell art stored in a string, 4 even segments
		String emptyCell =    "============|          ||          ||          ||          |============"; 					// ASCII Cell art stored in a string, 4 even segments
		int cellSize = unmarkedCell.length()/6; 									// Gets size of 1 calendar cell

		int firstDayOfMonth = getDayOfWeek(getMonth(), 1) - 1;

		for (int i = 0; i < unmarkedCell.length(); i += cellSize){ 			// For each row in a cell
			for (int j = row*7; j < (row*7)+7; j++){ 								// For each day in a week
				// Initial Declarations
				int dayOfMonth = j+1 - firstDayOfMonth; 

				// Get cell type (marked, unmarked, or empty)
				String cell = unmarkedCell;
				if (j+1 - firstDayOfMonth == getDay()) { 
					cell = markedCell; 
				} else if (dayOfMonth > getLengthOfMonth(getMonth()) || dayOfMonth < 1) { 
					cell = emptyCell;
				} else if (eventsList[getMonth()-1][dayOfMonth-1] != null){
					cell = eventCell;
				}
				
				String cellRow = cell.substring(i, i+cellSize); 

				// Add cell row to output
									

				if (cellRow.indexOf("%3d") != -1){			// If row displays a number date, printf, else print normal
					output += String.format(cellRow, dayOfMonth);
				} else if (cell == eventCell){
					String event = String.format("%-30s", eventsList[getMonth()-1][dayOfMonth-1]);
					if (cellRow.indexOf("1") != -1){
						output += cellRow.replace("1111111111", String.format("%10s", event.substring(0, 10)));
					}else if (cellRow.indexOf("2") != -1 && event.length() > 10){
						output += cellRow.replace("2222222222", String.format("%10s", event.substring(10, 20)));
					}else if (cellRow.indexOf("3") != -1 && event.length() > 20){
						output += cellRow.replace("3333333333", String.format("%10s", event.substring(20, 30)));
					}else{
						output += cellRow;
					}
				}else{ 
					output += cellRow; 
				}
				
			}
			output += "\n"; // End current row
		}

		return output;
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
		return DAYS_PER_MONTH[month-1];
	}
	public static int getDayOfWeek(int month, int day){
		int dayOfYear = getDayOfYear(month, day);
		int firstDayOfYear = 1; // Day of week on first day of the year, 0-6, Sun-Sat
		return ((dayOfYear%7 + firstDayOfYear == 0 ? dayOfYear%7 + 7 + firstDayOfYear : dayOfYear%7 + firstDayOfYear)); // If day is a saturday, % returns 0 instead of 7. This fixes that. 
	}
}