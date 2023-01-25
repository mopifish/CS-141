// Drake Pickett
// 1.19.2023
// CS &141
// Lab #2
//
// This program uses user input to calculate gross and net pay for salaried and hourly employees
//
// For extra credit, I used all loop types, made overtime built in (and attempted to make 
//     employee selection more streamlined) as well as general added flashiness!

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DPPayroll{
	public static void main(String[] args){
      //Declare input
      Scanner input = new Scanner(System.in);
      
      //Constants
      double SALARIED_PAY = 5000.00;
      double TAX_MULT = 0.8;
      double OVERTIME_MULT = 2.0;
      
      //Startup Message
      System.out.println("===EMPLOYEE PAY CALCULATOR===");
      System.out.print("\n");
      
      
      //Get numEmployees
      System.out.print("Please enter number of employees: ");
      int numEmployees = input.nextInt();
      System.out.print("\n");
      
      //Get gross/net pay for every employee
      for (int i = 0; i < numEmployees; i++){
         
         //Retrieve and validate user input
         System.out.printf("\tEMPLOYEE %d OF %d", i, numEmployees);
         System.out.print("\nFor HOURLY  : Please Enter [# of Hours Worked] \nFor SALARIED: Please Enter [0] \n\n[Input]: ");
         int userInput = input.nextInt();
         while (userInput < 0){
            System.out.println("Please enter a valid number.");
            
            System.out.print("Please enter Hours Worked for Hourly, or press 0 for Salaried: ");
            userInput = input.nextInt();
         }
         
         //Output gross and net pay
         System.out.print("\n\n");
         if (userInput == 0){
            System.out.println("\tSALARIED EMPLOYEE");
            System.out.printf("Gross Pay: %10.2f%nNet Pay  : %10.2f%n", SALARIED_PAY, SALARIED_PAY*TAX_MULT );
         }else{
            System.out.println("\tHOURLY EMPLOYEE");
            
            System.out.print("Please enter your Hourly Pay: ");
            double hourlyPay = input.nextDouble();
            
            double grossPay = (userInput <= 40 ? userInput*hourlyPay : (40*hourlyPay) + (userInput-40)*hourlyPay*OVERTIME_MULT);
            System.out.printf("Gross Pay: %10.2f%nNet Pay  : %10.2f%n", grossPay, grossPay*TAX_MULT);
            
         }
         System.out.print("\n\n");
         
      }
	}

}