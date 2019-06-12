import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 */

/**
 * This class is intended to model an efficient approach at finding the first
 * recurring character in a String
 * 
 * @author Ian Fernandes
 * 
 */
public class FirstRecurringCharacter {

	/**
	 * O(n) solution Uses HashSet because contains() method is O(1) compared to O(n)
	 * contains() method of ArrayList
	 * 
	 * @param input
	 * @return
	 */
	public static String process(String input) {
		// Declare and instantiate a HashMap obj
		// More efficient than ArrayList in current situation
		HashSet<Character> set = new HashSet<Character>();

		// Loop through each character
		for (int i = 0; i < input.length(); i++) {
			// If the set already contains the character we are currently looking at, return
			// the character
			// as it is our first recurring pair.
			if (set.contains(input.charAt(i)))
				return Character.toString(input.charAt(i));
			// Otherwise, add the character to the set
			set.add(input.charAt(i));
		}

		// If there are no recurring pairs, return null.
		return null;
	}

	/**
	 * Scan in lines and send them to process()
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// Declare and instantiate Scanner obj to read from a file
		Scanner scan = new Scanner(new File("recurring.dat"));

		// While there are still lines to scan, process them.
		while (scan.hasNextLine())
			System.out.println(process(scan.nextLine()));

		scan.close();

	}

}
