// Drake Pickett
// 1.12.2023
// CS &141
// Lab #1
//
// This program is designed to print a business form using print, println, and printf statements.
//
// For extra credit, I used printf multiple times


public class DPBusinessReport{
	public static void main(String[] args){

		//Ascii Frog Variables
		String frogSmall = "  o\n_`O'_";
		String frogLarge = "              _         \n  __   ___.--'_`.  \n ( _`.'. -   'o` ) \n _\\.'_'      _.-'    \n( \\`. )    //\\`        \n \\_`-'`---'\\__,       \n  \\`        `-\\        \n   ` ";

		//Stored number variables
		int numFrogs = 1328000;
		int numEmployees = 300;
		double frogsPerEmployee = (double) numFrogs / numEmployees;

		//Print statements
		System.out.printf("%s Dear Upper Management,\n\n", frogSmall);

		System.out.println("The recent frog infestation has proven to be much more severe than we first though.");
		System.out.printf("Our numbers department has reported an estimated %,d frogs, or %5.2f frogs per employee. \n", numFrogs, frogsPerEmployee);
		//System.out.print("Not only does incessant croaking keep our employees on edge at all hours, ");
		//System.out.println("but Karen has claimed the breakroom coffee has become suspiciously 'frog flavored'.");
		System.out.println("While our employees at Frogatech(TM) are the best of the best, and understand the importance of strong company spirit, ");
		System.out.printf("I am starting to believe this many frogs is more of a hinderance than a help.\n%s\n\n", frogSmall);

		System.out.println("This report is not without good news, however.");
		System.out.println("Recent workplace investigations have shown that some specimens show a certain aptitude for our line of work.");
		System.out.println("The workplace has taken to calling these frogs 'Our Best Employees'.");
		System.out.print("While I was dubious of this bold claim at first, ");
		System.out.print("I have reluctantly been forced to concede that they are extraordinary.\n");

		System.out.println("For evidence, I have attached my own coworker, Charlemagne.");
		System.out.println("He is my best employee. \n");

		System.out.println("\t\t\t Sincerely, \n\t\t\t    Sharon");

		System.out.println(frogLarge);
	}

}