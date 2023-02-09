// Drake Pickett
// 2.9.23
// CS &141
// Lab #4 - Guessing Game
/* This program plays a simple guessing game with the user  */

// There are no bugs that I know of (aside from entering incorret type during input, but we have not discussed how to fix this)
// For extra credit I wrote very nice code that I like :)

import java.util.Scanner;
import java.util.Random;   

public class DPGuessingGame{
   public static void main(String[] args){
      // Stats
      int bestGame = 100*100;
      int numGames = 0;
      int totalGuesses = 0;

      // Game Intro
      printInstructions();
      
      // Game Loop
      do {
         numGames++;
         int numGuesses = playGame();
         bestGame = (numGuesses < bestGame ? numGuesses:bestGame);
         totalGuesses += numGuesses;
      } while (continuePlaying() == true);

      // Game results
      printResults(bestGame, numGames, totalGuesses);
      pauseTerminal();

   }

   // Setup Functions
   public static void printInstructions(){
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I will think of a number between 1 and");
      System.out.println("100 and will allow you to guess until");
      System.out.println("you get it. For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
   }

   // Game functions
   public static int playGame(){
      // Class declarations
      Random rand = new Random();

      // Constants
      final int MIN_RANGE = 1;
      final int MAX_RANGE = 100;

      final int ANSWER = rand.nextInt(MAX_RANGE) + MIN_RANGE; // Gets a random number between 1 and 100

      // Stats
      int guesses = 0;

      // Game loop
      System.out.println("\n\nI'm thinking of a number between 1 and 100...");
      int userGuess;
      do{
         guesses++;
         userGuess = getUserInt("Your guess? ");
      } while (checkAnswer(userGuess, ANSWER) != true);

      System.out.println("You got it right in " + guesses + " guess" + (guesses==1?"":"es"));
      return guesses;
   }
   public static boolean checkAnswer(int userGuess, int answer){
      // Checks users answer. Prints if higher or lower, else returns true
      if (userGuess < answer){
         System.out.println("It's higher");
         return false;
      } else if (userGuess > answer) {
         System.out.println("It's lower");
         return false;
      }

      return true;
   }
   public static boolean continuePlaying(){
      // Answer constants
      String YES_ANSWERS = "yes, yep, okay, ok, y, yeah";
      String NO_ANSWERS = "no, nope, nah";
      String ACCEPTED_ANSWERS = YES_ANSWERS + NO_ANSWERS;

      String userAnswer;
      do{
         userAnswer = getUserString("Do you want to play again? ").toLowerCase();
      } while (ACCEPTED_ANSWERS.indexOf(userAnswer) == -1);

      return YES_ANSWERS.indexOf(userAnswer) != -1;
   }
   
   // Ending Functions
   public static void printResults(int bestGame, int numGames, int totalGuesses){
      System.out.println("\n\nOverall Results: ");
      System.out.println("\ttotal games = " + numGames);
      System.out.println("\ttotal guesses = " + totalGuesses);
      System.out.println("\tguesses/game = " + (double)totalGuesses/(double)numGames);
      System.out.println("\tbest game = " + bestGame);
   }

   // Utility functions
   public static String getUserString(String message){
      Scanner input = new Scanner(System.in);
      System.out.print(message);
      return input.next();
   } 
   public static int getUserInt(String message){
      Scanner input = new Scanner(System.in);
      System.out.print(message);
      return input.nextInt();
   }
   public static void pauseTerminal(){
      Scanner input = new Scanner(System.in);
      System.out.print("\n\nPress enter to quit...");
      input.nextLine();
   }


}