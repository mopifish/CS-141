// Drake Pickett
// 3.7.23
// CS&141
/* This function takes a given input file of DNA Sequences, and outputs it to a given output file
	in a specified, and calculated, format.				*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Calendar;
import java.io.*;

public class DPDNA{
	// --- Class Constants ---
	private static final double[] NUCLEOTIDE_MASSES = {135.128, 111.103, 151.128, 125.107, 100.0}; // A(denine), C(ytosine), G(uanine), T(hymine), J(unk)
	private static final int MIN_CODONS_IN_PROTEIN = 5;
	private static final double MIN_CG_PERCENT_IN_PROTEIN = 30.0;
	private static final int NUM_UNIQUE_NUCLEOTIDES = 4;
	private static final int NUM_NUCLEOTIDES_IN_CODON = 3;

	// --- Main Methods ---
	public static void main(String[] args) throws FileNotFoundException{
		// Introduction
		System.out.println("=== DNA NUCLEOTIDE INFORMATION SYSTEM");
		System.out.println("=== WELCOME TO THE D.N.I.S.\n");

		// Main Processes
		Scanner inputFile = new Scanner(getExistingFile("INPUT FILE NAME: ", "InputFiles"));
		PrintStream outputFile = new PrintStream(new File("OutputFiles\\" + getUserInput("OUTPUT FILE NAME: ")));

		System.out.println("\nWRITING TO FILE...");

		// Start writing output file
		outputFile.println("=== D.N.I.S. OUTPUT FORM");
		outputFile.println("=== DATE OF RETRIEVAL: " + getDate());
		outputFile.println("=== HOUR OF RETRIEVAL: " + getTime());
		outputFile.println("\n");

		// Starts first line of file
		String line = inputFile.nextLine();
		while (inputFile.hasNextLine()) {
			String sequenceName = "";
			String nucleotides = "";

			// Start of new sequence
			if (line.contains(" ")){
				sequenceName = line;

				// Get entire nucleotide sequence (in case it spans multiple lines)
				while (inputFile.hasNextLine()){
					line = inputFile.nextLine();
					if (line.contains(" ") == false){
						nucleotides += line;
					}else{
						break;
					}
				}

			}

			nucleotides = nucleotides.toUpperCase();

			writeSequenceToFile(sequenceName, nucleotides, outputFile);
		} 
		System.out.println("WRITING COMPLETE\n");
		getUserInput("Enter to quit");
	} 

	public static void writeSequenceToFile(String sequenceName, String nucleotides, PrintStream outputFile){
		outputFile.println("REGION NAME: " + sequenceName.toUpperCase());
		outputFile.println("===================================================");
		outputFile.println("NUCLEOTIDES: " + nucleotides);
		outputFile.println("NUC. COUNTS: " + Arrays.toString(getNucleotideCounts(nucleotides)));
		outputFile.println("TOTAL MASS %: " + Arrays.toString(getMassPercents(nucleotides)) + " OF " + getTotalMass(nucleotides));
		outputFile.println("CODONS LIST : " + Arrays.toString(getCodonsList(nucleotides)));
		
		outputFile.println("\nPROTEIN STATUS : " + (isProtein(nucleotides) ? "VALID" : "INVALID"));
		outputFile.println("\n");
	}

	// --- DNA Math Methods ---
	public static int[] getNucleotideCounts(String nucleotides){
		int[] nucCount = {getNumOfChar(nucleotides, 'A'), getNumOfChar(nucleotides, 'C'), getNumOfChar(nucleotides, 'G'), getNumOfChar(nucleotides, 'T')};
		return nucCount;
	}

	public static double[] getMassPercents(String nucleotides){
		int[] nucCount = getNucleotideCounts(nucleotides);
		double[] massPercents = new double[NUM_UNIQUE_NUCLEOTIDES];

		double totalMass = getTotalMass(nucleotides);

		for(int i = 0; i < nucCount.length; i++){
			int nucleotide = nucCount[i]; 
			massPercents[i] = round(((nucleotide * NUCLEOTIDE_MASSES[i])/totalMass * 100), 1);
		}

		return massPercents;
	}
	public static double getTotalMass(String nucleotides){
		int[] nucCount = getNucleotideCounts(nucleotides);

		double totalMass = 0;
		for(int i = 0; i < nucCount.length; i++){
			int numNucs = nucCount[i];
			totalMass += NUCLEOTIDE_MASSES[i] * numNucs;
		}

		totalMass += NUCLEOTIDE_MASSES[4] * getNumOfChar(nucleotides, '-'); // Adds mass from Junk proteins

		return round(totalMass, 1);
	}

	public static String[] getCodonsList(String nucleotides){
		nucleotides = nucleotides.replace("-", ""); // Removes junk protein
		int numCodons = nucleotides.length()/NUM_NUCLEOTIDES_IN_CODON;

		String[] codonsList = new String[numCodons];

		for (int i = 0; i < numCodons; i++){
			codonsList[i] = nucleotides.substring(i*NUM_NUCLEOTIDES_IN_CODON, i*NUM_NUCLEOTIDES_IN_CODON + NUM_NUCLEOTIDES_IN_CODON);
		}

		return codonsList;
	}

	public static boolean isProtein(String nucleotides){
		String[] codonsList = getCodonsList(nucleotides);
		double[] massPercents = getMassPercents(nucleotides);

		String[] validStartCodons = {"ATG"};
		String[] validEndCodons = {"TAA", "TAG", "TGA"};

		int numCodons = nucleotides.length()/NUM_NUCLEOTIDES_IN_CODON;
		double cAndGMassPercent = massPercents[1] + massPercents[2];

		return isValueInList(codonsList[0], validStartCodons) // If it starts with the valid start codon
				&& isValueInList(codonsList[numCodons-1], validEndCodons) // Ends with a valid end codon
				&& numCodons >= MIN_CODONS_IN_PROTEIN // Has greater than 5 total codons
				&& cAndGMassPercent >= MIN_CG_PERCENT_IN_PROTEIN; // And the C and G nucleotides have a combined mass greater than 30%

	}

	// --- File Methods ---
	public static File getExistingFile(String prompt, String directory){
		File file;

		while (true){
			file = new File((directory != "" ? directory + "\\" : "") + getUserInput(prompt));
			if (file.exists()){ break; }
			System.out.println("That title didn't work! Try again \n");
		}

		return file;
	}

	// --- Input Methods ---
	public static String getUserInput(String prompt){
		Scanner input = new Scanner(System.in);

		System.out.print(prompt);
		return input.nextLine();
	}

	// --- Utility Methods ---
	public static int getNumOfChar(String string, char c){
		int count = 0;

		for (int i = 0; i < string.length(); i++){
			if (string.charAt(i) == c){
				count ++;
			}
		}

		return count;
	}
	public static double round(double number, int decimalPlace){
		// Rounds the given numebr to the given decimal place
		return Math.round(number * Math.pow(10.0, decimalPlace))/Math.pow(10.0, decimalPlace);
	}
	public static boolean isValueInList(String value, String[] list){
		return Arrays.toString(list).contains(value);
	}
	public static String getDate(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
        
        return month+1 + "." + day + "." + year;
	}
	public static String getTime(){
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        return hour + ":" + minute + ":" + second;
	}
} 