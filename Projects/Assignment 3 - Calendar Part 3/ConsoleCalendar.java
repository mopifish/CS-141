/* Console Calendar Class
	Calendar object that stores dates and events. 
	Can be printed
*/

import java.util.Calendar;

public class ConsoleCalendar {
	
	private static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private static final int[] DAYS_PER_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	private String picture = "┌───────────────────────────────────────────────┐\n│                                    ===========│\n│                                    ===========│\n│                                      =========│\n│                 #######              =========│\n│                ##     ###            =========│\n│               ##@ v @   ##            ========│\n│       =       #     ###  #            ========│\n│       ==      #      ## ###           ========│\n│        ====   ###      #####     =============│\n│           ==    ########    ==================│\n│          ========^===^========================│\n│                       ========================│\n│    bird             ===              =========│\n│                                        =======│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n│                                       ========│\n└───────────────────────────────────────────────┘\n";
	private Date date;

	// --- Constructor ---
	public ConsoleCalendar(int month, int day){
		// Calendar initializes on current day/month
		Calendar cal = Calendar.getInstance();
		this.date = new Date(month + "/" + day);
	}


	// --- Setget Functions ---
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
		String output = "\n\t\t   " + MONTH_NAMES[getMonth()-1] + "\n\n";
		output += "  Sun    Mon    Tue    Wed    Thu    Fri    Sat  \n";
		for (int i = 0; i < 5; i++){
			output += weekToString(i);
		}

		return output;
	}
	public String weekToString(int row){
		String output = "";

		String unmarkedCell = "==================|             %3d||                ||                |=================="; 				// ASCII Cell art stored in a string, 4 even segments
		String markedCell =   "==================|xxxxxxxxxxxxx%3d||xxxxxxxxxxxxxxxx||xxxxxxxxxxxxxxxx|=================="; 					// ASCII Cell art stored in a string, 4 even segments
		String emptyCell =    "==================|                ||                ||                |=================="; 					// ASCII Cell art stored in a string, 4 even segments
		int cellSize = unmarkedCell.length()/5; 									// Gets size of 1 calendar cell

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
				}
				
				String cellRow = cell.substring(i, i+cellSize); 

				// Add cell row to output
				if (cellRow.indexOf("%3d") != -1){			// If row displays a number date, printf, else print normal
					output += String.format(cellRow, dayOfMonth);
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