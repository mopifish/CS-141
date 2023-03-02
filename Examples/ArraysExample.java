// Drake Pickett
// 2.27.23
// Arrays Example

import java.util.Scanner;

public class ArraysExample{
	public static void main(String[] args){
		// type[] name = new type[length]

		// Creates an array of 10 integers
		int[] numbers = new int[10];

		// Arrays are initialized full of "zero-equivalent" values.
		// For an integer array, it is initialized full of 0's
		// You can access an element using []
		System.out.println(numbers[0]);
		System.out.println(numbers[1]);

		// But if you try to callan index out of bounds, it'll throw an error
		// System.out.println(numbers[10]); 
		// System.out.println(numbers[-1]);

		// Use .length to get the length of an array
		System.out.println(numbers.length);

		// You can go through an array using for loop
		for (int i =0; i < numbers.length; i++){
			System.out.print(numbers[i] + " ");
		}


		System.out.println("\nPress enter to quit...");
		Scanner input = new Scanner(System.in);
		input.nextLine();

	}
}