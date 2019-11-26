/**
 * Demonstration of the Flood Fill algorithm on a 2D array
 * 
 * @author Ian Fernandes
 *
 */
public class FloodFill {
	private static char[][] grid = 
		{
			{ 'L', 'L', 'L', 'L', 'L' },
			{ 'L', 'W', 'L', 'W', 'W' },
			{ 'L', 'W', 'L', 'L', 'W' },
			{ 'L', 'W', 'W', 'W', 'W' },
			{ 'L', 'L', 'L', 'L', 'L' }
		};

	private static int[] rDirections = { -1, 0, 1, 0 };
	private static int[] cDirections = { 0, 1, 0, -1 };

	/**
	 * Floodfill algorithm: returns number of vertices that are in the same component
	 * as the vertex given by [r][c].
	 * 
	 * Being in the same component in a 2D grid implies that both vertices' values are
	 * the same char (specifically, c1).
	 * 
	 * Marks visited vertices using c2.
	 * 
	 * @param r the row value of the vertex to start from
	 * @param c the column value of the vertex to start from
	 * @param c1 the value of the component to track
	 * @param c2 the value to mark component with
	 * @return the number of vertices in the requested component
	 */
	private static int floodfill(int r, int c, char c1, char c2) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length) // If given vertex does not exist
			return 0;
		if (grid[r][c] != c1) // If current vertex not part of component
			return 0;
		
		int no = 1; // # of vertices so far in component is 1
		grid[r][c] = c2; // visited
		
		//Cycle through North, East, South, and West vertices
		for (int i = 0; i < 4; i++) {
			no += floodfill(r + rDirections[i], c + cDirections[i], c1, c2);
		}
		return no;
	}

	/**
	 * Print the array out so that the user can see the component "path" very easily
	 */
	private static void printArr() {
		for (char[] r : grid) {
			for (char element : r)
				System.out.print(element + " ");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println(floodfill(1,3,'W','.'));
		printArr();
	}

}
