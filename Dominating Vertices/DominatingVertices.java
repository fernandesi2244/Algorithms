import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Vertex X dominates vertex Y if every path from the a start vertex (vertex 0
 * in this problem) to Y must go through X. If Y is not reachable from the start
 * vertex, then Y does not have any dominator. Every vertex reachable from the
 * start vertex dominates itself. Given a directed graph, determine the
 * dominators of every vertex.
 * 
 * This class assumes that with a graph of size v, vertices will be labeled
 * from 0 to v-1.
 * @author Ian Fernandes
 *
 */
public class DominatingVertices {

	private LinkedList<Integer>[] vertices;
	private boolean[] visited;
	private int startVertex;

	/**
	 * Each instance of DominatingVertices represents a graph.
	 * 
	 * @param v      number of vertices
	 * @param startV the vertex to start traversals from
	 */
	@SuppressWarnings("unchecked")
	private DominatingVertices(int v, int startV) {
		vertices = new LinkedList[v];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new LinkedList<Integer>();
		}
		visited = new boolean[v];
		startVertex = startV;
	}

	private void addEdge(int from, int to) {
		vertices[from].add(to);
	}

	/**
	 * Depth-first search for determining reachable vertices
	 * 
	 * @param vertex
	 */
	private void dfs(int vertex) {
		visited[vertex] = true;

		Iterator<Integer> it = vertices[vertex].listIterator();
		while (it.hasNext()) {
			int nextVertex = it.next();
			if (!visited[nextVertex])
				dfs(nextVertex);
		}
	}

	/**
	 * Depth-first search for determining of a route still exists from start vertex
	 * to destination given obstacle vertex of stopVertex (Can we still get to the 
	 * destination without going through stopVertex? If so, then stopVertex is not
	 * a dominator of the destination.)
	 * 
	 * @param vertex
	 * @param destination
	 * @param stopVertex
	 * @return
	 */
	private boolean dfs(int vertex, int destination, int stopVertex) {
		visited[vertex] = true;

		if (vertex == stopVertex)
			return false;

		if (vertex == destination)
			return true;

		Iterator<Integer> it = vertices[vertex].listIterator();
		while (it.hasNext()) {
			int nextVertex = it.next();
			if (!visited[nextVertex] && nextVertex != stopVertex)
				if (dfs(nextVertex, destination, stopVertex))
					return true;
		}

		return false;
	}

	/**
	 * Reset visited array to contain only false values.
	 * This allows the visited array to be reused in the dfs() method
	 */
	private void resetVisitedArray() {
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("dominators.dat"));

		// Declare new graph
		int noVertices = Integer.parseInt(scan.nextLine());
		int startV = Integer.parseInt(scan.nextLine());
		DominatingVertices graph = new DominatingVertices(noVertices, startV);

		// Initialize graph
		while (scan.hasNextLine()) {
			int first = scan.nextInt();
			int second = Integer.parseInt(scan.nextLine().trim());
			graph.addEdge(first, second);
		}

		graph.dfs(0);

		ArrayList<Boolean> reachable = new ArrayList<>();

		/*
		 * Loop through reachable vertices and add them to reachable list Reset visited
		 * array in the process
		 */
		for (int i = 0; i < graph.visited.length; i++) {
			reachable.add(i, graph.visited[i]);
			graph.visited[i] = false;
		}

		//Initialize dominators ArrayList for each vertex
		ArrayList<ArrayList<Integer>> dominators = new ArrayList<>();
		for (int i = 0; i < graph.vertices.length; i++) {
			dominators.add(new ArrayList<Integer>());
		}

		//Find dominators of every reachable vertex.
		for (int i = 0; i < reachable.size(); i++) {
			if (reachable.get(i)) {
				for (int j = 0; j < reachable.size(); j++) {
					if (i == j || !graph.dfs(graph.startVertex, i, j))
						dominators.get(i).add(j);
					graph.resetVisitedArray();
				}
			}
		}

		//Print dominators of each vertex (if applicable)
		for (int i = 0; i < dominators.size(); i++) {
			if (dominators.get(i).isEmpty()) {
				System.out.println(i + " does not have any dominators.");
			} else {
				System.out.print("The dominators of " + i + " are: ");
				for (int j = 0; j < dominators.get(i).size(); j++) {
					System.out.print(dominators.get(i).get(j) + " ");
				}
				System.out.println();
			}
		}

		scan.close();

	}

}
