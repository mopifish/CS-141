// Drake Pickett
// 2.23.23
// CS&141
/* This program uses provided text files to play a simple game of madlibs with the user.
	It reads empty madlibs from the "Empty Madlibs" folder, and outputs finished madlibs
	to the "Finished Madlibs" folder.

	For EC, I wrote a very nice and simple program that works well :)					*/

import java.util.Scanner;
import java.io.*;


public class DPMadLibs{
	public static void main(String[] args) throws FileNotFoundException{
		boolean isPlaying = true;

		System.out.println("Welcome to Mad Libs!");

		while (isPlaying){
			System.out.println();
			System.out.println("[C]reate a new Mad Lib");
			System.out.println("[V]iew an old Mad Lib");
			System.out.println("[Q]uit Mad Libs");
			System.out.println();

			switch (getUserInput("> ").toUpperCase()){
				case "C":
					createNewMadlib();
					break;
				case "V":
					viewExistingMadlib();
					break;
				case "Q":
					isPlaying = false;
					break;
				default:
					System.out.println("Please enter C, V, or Q!\n");
					break;
			}
		}

		System.out.println("Thanks for playing!");
	}


	public static void createNewMadlib() throws FileNotFoundException{
		Scanner emptyMadLib = new Scanner(getExistingFile("Existing Mad Lib Title: ", "Empty Madlibs"));
		PrintStream newMadLib = new PrintStream(new File("Finished Madlibs\\" + getUserInput("New Mad Lib Title: ") + ".txt"));

		while (emptyMadLib.hasNextLine()){
			String line = emptyMadLib.nextLine();

			while (line.contains("<") && line.contains(">")){
				String placeholderToken = line.substring(line.indexOf("<"), line.indexOf(">")+1).toLowerCase();
				String userAnswer = getUserInput("Please enter " + ("aeiou".contains(placeholderToken.substring(1, 2)) ? "an " : "a ") + placeholderToken.substring(1, placeholderToken.length() - 1) + ": ");
				line = line.replace(placeholderToken, userAnswer.strip());
			}

			newMadLib.println(line);
		}

	}

	public static void viewExistingMadlib() throws FileNotFoundException{
		Scanner madLib = new Scanner(getExistingFile("Existing Mad Lib Title: ", "Finished Madlibs"));

		while (madLib.hasNextLine()){
			System.out.println(madLib.nextLine());
		}
	}


	// --- File Methods ---
	public static File getExistingFile(String prompt, String directory){
		File file;

		while (true){
			file = new File(directory + "\\" + getUserInput(prompt) + ".txt");
			if (file.exists()){ break; }
			System.out.println("That title didn't work! Try again \n");
		}

		return file;
	}


	// --- Input Commands ---
	public static String getUserInput(String prompt){
		Scanner input = new Scanner(System.in);

		System.out.print(prompt);
		return input.nextLine();
	}


}