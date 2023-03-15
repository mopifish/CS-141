public class Date {

	private int month;
	private int day;

	public Date(String date){
		this.month = getMonthFromDate(date);
		this.day = getDayFromDate(date);
	}

	public void setDate(String date){
		setDay(getDayFromDate(date));
		setMonth(getMonthFromDate(date));
	}

	public void setMonth(int newMonth){
		month = newMonth;
	}
	public void setDay(int newDay){
		day = newDay;
	}

	public int getMonth(){
		return month;
	}

	public int getDay(){
		return day;
	}

	public String toString(){
		return "Month: " + month + "\nDay: " + day;
	}

	public static int getMonthFromDate(String date){
		return Integer.parseInt(date.substring(0, date.indexOf("/"))); // Substrs mm/dd from first letter up to slash (non-inclusive)
	}
	public static int getDayFromDate(String date){
		return Integer.parseInt(date.substring(date.indexOf("/")+1)); // Substrs mm/dd from letter after slash to end
	}
}