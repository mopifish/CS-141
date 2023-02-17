import java.io.*;
import java.util.Scanner;

public class FileReadingExample{
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("Example.txt");
		Scanner input = new Scanner(f);
		String text = input.nextLine();
		System.out.println("text");

		
		pause();
	}

	public static void pause() throws IOException{
		System.in.read();
	}
}