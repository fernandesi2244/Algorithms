import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class to represent finding connected components in an undirected graph
 * 
 * @author Ian Fernandes
 *
 */
public class Components {

	/**
	 * Adjacency List representation of graph
	 */
	private LinkedList<Integer>[] vertices;

	/**
	 * Visited array to determine if a given vertex has already been "visited" by
	 * dfs
	 */
	boolean[] visited;

	/**
	 * Represents a vertex
	 */
	static class Point {
		int location;
		Point parent;

		public Point(int location, Point parent) {
			this.location = location;
			this.parent = parent;
		}

	}

	/**
	 * Construct a new graph with v vertices
	 * 
	 * @param v no of vertices in graph
	 */
	@SuppressWarnings("unchecked")
	public Components(int v) {
		vertices = new LinkedList[v];
		for (int i = 0; i < v; i++)
			vertices[i] = new LinkedList<>();
		visited = new boolean[v];
	}

	/**
	 * Add an extraverted (bidirectional) edge from vertex a to vertex b
	 * 
	 * Assume: a and b are valid vertices within graph
	 * 
	 * @param a
	 * @param b
	 */
	private void addEdge(int a, int b) {
		vertices[a].add(b);
		vertices[b].add(a);
	}

	/**
	 * Depth-first search used in finding # of connected components
	 * 
	 * @param start vertex to start dfs from
	 */
	private void dfs(int start) {
		visited[start] = true;
		Iterator<Integer> adjacentVertices = vertices[start].iterator();
		while (adjacentVertices.hasNext()) {
			int nextVertex = adjacentVertices.next();
			if (!visited[nextVertex])
				dfs(nextVertex);
		}
	}

	/**
	 * Counts number of connected components in the graph using dfs
	 * 
	 * @return no of connected components
	 */
	private int noComponents() {
		int no = 0;
		for (int i = 0; i < vertices.length; i++) {
			if (!visited[i]) {
				dfs(i);
				no++;
			}
		}
		return no;
	}

	public static void main(String[] args) {
		Components graph = new Components(8);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(5, 3);
		graph.addEdge(6, 7);

		System.out.println(graph.noComponents());

	}
}
