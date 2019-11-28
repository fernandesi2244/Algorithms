import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class to represent Dijkstra's Shortest Path Algorithm
 * 
 * @author Ian Fernandes
 *
 */
public class DijkstrasShortestPath {

	/**
	 * Representation of graph in adjacency matrix
	 */
	private static int[][] adjMat;

	/**
	 * Holds shortest distances for each vertex from vertex 0
	 */
	private static int[] shortestDistanceFrom0;

	/**
	 * Holds the last vertex used to get to the vertex at index i (Used to calculate
	 * path later)
	 */
	private static int[] lastVertex;
	private static Queue<Integer> unvisited;

	/**
	 * Initialize arrays to appropriate values to begin Dijkstra's algorithm
	 */
	private static void initializeArrays() {
		adjMat = new int[5][5];
		shortestDistanceFrom0 = new int[5];
		lastVertex = new int[5];
		// Set up queue to sort vertices from lowest path length to highest path length
		// to support O(1) operation in algorithm
		unvisited = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer first, Integer second) {
				return shortestDistanceFrom0[first] - shortestDistanceFrom0[second];
			}
		});

		for (int i = 0; i < shortestDistanceFrom0.length; i++) {
			shortestDistanceFrom0[i] = Integer.MAX_VALUE;
			lastVertex[i] = -1;
		}

		// Distance of vertex 0 from itself is 0
		shortestDistanceFrom0[0] = 0;

		// Every vertex is unvisited at the beginning
		for (int i = 0; i < 5; i++) {
			unvisited.add(i);
		}

		// Construct sample graph
		addEdge(0, 1, 6);
		addEdge(0, 3, 1);
		addEdge(1, 3, 2);
		addEdge(3, 4, 1);
		addEdge(1, 4, 2);
		addEdge(1, 2, 5);
		addEdge(2, 4, 5);

	}

	/**
	 * Add bidirectional edge from vertex a to vertex b with appropriate weight
	 * 
	 * @param a      first vertex
	 * @param b      second vertex
	 * @param weight weight of edge between vertex a and b
	 */
	private static void addEdge(int a, int b, int weight) {
		adjMat[a][b] = weight;
		adjMat[b][a] = weight;
	}

	private static void dijkstrasAlgorithm() {
		while (!unvisited.isEmpty()) {
			// Get next smallest (path) unvisited vertex
			int currentVertex = unvisited.remove();
			int[] currentVertexNeighbors = adjMat[currentVertex];

			// For each unvisited neighbor
			for (int i = 0; i < currentVertexNeighbors.length; i++) {
				if (currentVertexNeighbors[i] != 0 && unvisited.contains(i)) {
					int distanceFrom0 = shortestDistanceFrom0[currentVertex] + currentVertexNeighbors[i];
					if (distanceFrom0 < shortestDistanceFrom0[i]) {
						shortestDistanceFrom0[i] = distanceFrom0;
						lastVertex[i] = currentVertex;
					}
				}
			}
			updateQueue();
		}
	}

	/**
	 * Update PriorityQueue unvisited, as values of shortest paths have most likely
	 * changed after last insertion into queue
	 */
	private static void updateQueue() {
		HashSet<Integer> temp = new HashSet<>();
		while (!unvisited.isEmpty())
			temp.add(unvisited.remove());
		temp.forEach(x -> unvisited.add(x));
	}

	/**
	 * Calculate the shortest path from each vertex to vertex 0
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		initializeArrays();
		dijkstrasAlgorithm();
		for (int i = 0; i < 5; i++) {
			// Print shortest path length
			System.out.print(i + ": " + shortestDistanceFrom0[i]);
			System.out.print(" ||| ");

			// Print shortest path sequence
			ArrayList<Integer> path = new ArrayList<>();
			int a = i;
			while (lastVertex[a] != -1) {
				path.add(a);
				a = lastVertex[a];
			}
			path.add(0);
			Collections.reverse(path);
			System.out.println(path);
		}

	}

}
