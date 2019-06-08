/**
 * 
 */
package Other;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ian Fernandes This class reads matrices from a text file and
 *         multiplies them if possible.
 */
public class MatrixMultiplication {

	/**
	 * Precondition: "first" represents contents of first matrix and "second"
	 * represents contents of second matrix Postcondition: "first" and "second"
	 * remain unchanged and resultant array/matrix is printed to the console.
	 * 
	 * @param first
	 * @param second
	 */
	public static void multiply(int[][] first, int[][] second) {

		// If matrices cannot be multiplied together, stop calculations and print error
		// message.
		if (first[0].length != second.length) {
			System.out.println(
					"Matrix multiplication is invalid. Columns of first matrix do not equal rows of second matrix!");
			return;
		}

		// Instantiate new resultant array based on dimensions of "first" and "second".
		int[][] resultant = new int[first.length][second[0].length];

		// Perform matrix multiplication
		for (int a = 0; a < resultant.length; a++) {
			for (int b = 0; b < resultant[0].length; b++) {
				for (int c = 0; c < second.length; c++) {
					resultant[a][b] += first[a][c] * second[c][b];
				}
			}
		}

		System.out.println("The resultant array is: ");

		// Print resultant array
		for (int[] a : resultant) {
			for (int b : a)
				System.out.printf("%-4d", b);
			System.out.println();
		}

		System.out.println("|||||||||||||||||||||||");
	}

	/**
	 * Scan in test data from "MatrixMultiplication.dat" file and process it with
	 * multiply() method
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("MatrixMultiplication.dat"));
		int no = Integer.parseInt(scan.nextLine());
		while (no-- > 0) {
			// Scan in # of rows and columns of first matrix
			int row1 = Integer.parseInt(scan.next());
			int col1 = Integer.parseInt(scan.next().trim());

			// Instantiate first matrix
			int[][] first = new int[row1][col1];

			// Scan in first matrix using row-major traversal
			for (int r = 0; r < row1; r++) {
				for (int c = 0; c < col1; c++) {
					first[r][c] = Integer.parseInt(scan.next().trim());
				}
			}

			// Scan in # of rows and columns of second matrix
			int row2 = Integer.parseInt(scan.next());
			int col2 = Integer.parseInt(scan.next().trim());

			// Instantiate second matrix
			int[][] second = new int[row2][col2];

			// Scan in second matrix using row-major traversal
			for (int r = 0; r < row2; r++) {
				for (int c = 0; c < col2; c++) {
					second[r][c] = Integer.parseInt(scan.next().trim());
				}
			}

			// Process both matrices (multiply them)
			multiply(first, second);

		}
		scan.close();
	}

}
