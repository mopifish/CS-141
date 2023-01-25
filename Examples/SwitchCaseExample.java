//CS 141 
//Drake Pickett
// 1/23/2023
//Switch case Statement Example

public class SwitchCaseExample{
	public static void main(String[] args){
		
		for (int i = 0; i < 5; i++){

			int grade = (int)(Math.random()*100); //Generate random number between 1 and 100
			System.out.println("Number Grade: " + grade); //Print out number for reference


			/*
				IMPORTANT NOTE:
				
				Break statements are CRUCIAL to a Switch Case statement
				Once one case is matched, it will execute all following cases 
				(even if they dont match) until a break is met
			*/

			switch (grade/10) {
				case 10: //grade is a perfect 100!
					System.out.println("A+!");
					break;
				case 9: // Grade is between 90 and 100
					System.out.println("A");
					break;
				case 8: //Grade is between 80 and 90
					System.out.println("B");
					break;
				case 7: //Grade is between 70 and 80
					System.out.println("C");
					break;
				case 6: //Grade is between 60 and 70
					System.out.println("D");
					break;
				default://Grade does not match any of the above
					System.out.println("F!!!!");
					break;
			}//End of Switch

		}//End of For Loop
	}//End of Main function
}//End of SwitchCaseExample class
