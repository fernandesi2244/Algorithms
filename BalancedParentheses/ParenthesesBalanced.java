/**
 * 
 */
package fundamentals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Ian Fernandes 
 * 		   This class reads input from a text file that contains
 *         parentheses expressions like "{([])}" and "{(]". It then determines
 *         whether the parentheses are properly balanced. For example, "[()]{}"
 *         is balanced while "[(])" is not balanced.
 */
public class ParenthesesBalanced {

	/**
	 * This method determines whether the given input String is balanced or not
	 * utilizing a stack.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean process(String input) {

		// Create a new stack to handle parentheses characters.
		Stack<Character> stack = new Stack<>();

		// Iterate through input String
		for (int i = 0; i < input.length(); i++) {

			/*
			 * The following conditionals test if the current character at i is equal to a
			 * right parentheses character. If so, it pops from the top of the stack to see
			 * if that character corresponds to the current character at i. If not, return
			 * false.
			 */
			if (input.charAt(i) == ')') {
				char popped = stack.pop();
				if (popped != '(')
					return false;
			} else if (input.charAt(i) == ']') {
				char popped = stack.pop();
				if (popped != '[')
					return false;
			} else if (input.charAt(i) == '}') {
				char popped = stack.pop();
				if (popped != '{')
					return false;
			} else // If current character at i is not a right parentheses character, push it to
					// the stack.
				stack.push(input.charAt(i));

		}

		return true;
	}

	/**
	 * The main method reads input from the file "parentheses.dat" and sends them to
	 * the process() method to be interpreted as balanced or unbalanced.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		// Create new Scanner to handle file input
		Scanner scan = new Scanner(new File("parentheses.dat"));

		// # of test cases
		int no = Integer.parseInt(scan.nextLine());

		// variable to keep track of expression #
		int copy = no;

		while (no-- > 0) {

			// Scans in next line from text file
			String current = scan.nextLine();

			// Prints out current expression
			System.out.println("Parentheses expression #" + (copy - no) + ": " + current);

			// Prints out results of evaluating current expression
			System.out.println(process(current) ? "Parentheses expression #" + (copy - no) + " IS balanced.\n"
					: "Parentheses expression #" + (copy - no) + " IS NOT balanced.\n");
		}

		scan.close();
	}

}
