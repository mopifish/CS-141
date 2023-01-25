import java.util.Scanner;

public class Addition{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		System.out.print("Enter first integer: ");
		int number3 = input.nextInt();

		System.out.print("Enter second integer: ");
		int number2 = input.nextInt();

		int sum = number3 + number2;

		System.out.printf("Sum is %d%n", sum);
	}

}