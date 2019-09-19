import java.util.*;
import java.io.*;

/**
 * This class is intended to demonstrate a basic maze solver using recursion. 
 * It only determines if a path exists, and if so, displays the number of steps it took to get there.
 * It does not find the smallest number of steps to get to the destination.
 * @author Ian Fernandes
 *
 */
public class Maze_DoesPathExist {

	private static int currentSteps;
	private static char[][] maze;
	private static ArrayList<Character> closedSymbols = new ArrayList<>(Arrays.asList('#', 'x', '+'));
	private static boolean pathFound;

	/**
	 * Recursive method to maneuver to the destination.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private static boolean find(int row, int col) {

		if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length)
			return false;
		if (maze[row][col] == 'E') {
			System.out.println("One path found.");
			pathFound = true;
			return true;
		}
		if (closedSymbols.contains(maze[row][col]))
			return false;

		maze[row][col] = '+';
		currentSteps++;
		printArr(); // Use this for debugging or examining each step of maze
		// navigation.

		// CHECK ALL DIRECTIONS (Can be edited to support other movement)

		// north
		if (find(row - 1, col)) {
			return true;
		}

		// east
		if (find(row, col + 1)) {
			return true;
		}

		// south
		if (find(row + 1, col)) {
			return true;
		}

		// west
		if (find(row, col - 1)) {
			return true;
		}

		// Mark the entry as closed if it is a dead end and decrement the step count.
		maze[row][col] = 'x';
		currentSteps--;
		return false;

	}

	/**
	 * Print the array at each step.
	 */
	private static void printArr() {
		for (char[] a : maze) {
			for (char b : a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Process each input entry and send input to a method to determine if a route exists.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("maze.dat"));
		int noDataSets = Integer.parseInt(scan.nextLine());

		// Process each data set
		while (noDataSets-- > 0) {
			System.out.println("//////////////////////\nStart of Analysis\n");
			int rowStart = -1, colStart = -1; // Initialize to -1 to help with debugging shall it be necessary.
			currentSteps = 0;
			pathFound = false;
			int dimensions = Integer.parseInt(scan.nextLine());
			maze = new char[dimensions][dimensions];

			// Load input into array
			for (int r = 0; r < dimensions; r++) {
				char[] currentRow = scan.nextLine().toCharArray();
				for (int c = 0; c < dimensions; c++) {
					maze[r][c] = currentRow[c];

					// find starting position
					if (maze[r][c] == 'S') {
						rowStart = r;
						colStart = c;
					}
				}
			}

			// start maze-run from starting position
			find(rowStart, colStart);
			System.out.println(pathFound ? "Number of Steps: " + currentSteps : "NOT POSSIBLE");
			System.out.println("\nEnd of Analysis\n//////////////////////\n\n\n");

		}

		scan.close();
	}
}
