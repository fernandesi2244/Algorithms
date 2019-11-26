import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class to demonstrate a topological sort on a Directed Acyclic Graph
 * 
 * @author Ian Fernandes
 *
 */
public class TopologicalSort {

	/**
	 * Adjacency List representation of graph (DAG)
	 */
	private LinkedList<Integer>[] vertices;

	/**
	 * Visited array to determine if a given vertex has already been "visited" by
	 * dfs
	 */
	boolean[] visited;

	/**
	 * ArrayList representing topo sort (linear)
	 */
	ArrayList<Integer> topoPath;

	/**
	 * Construct a new graph with v vertices
	 * 
	 * @param v no of vertices in graph
	 */
	@SuppressWarnings("unchecked")
	public TopologicalSort(int v) {
		vertices = new LinkedList[v];
		for (int i = 0; i < v; i++)
			vertices[i] = new LinkedList<>();
		visited = new boolean[v];
		topoPath = new ArrayList<>();
	}

	/**
	 * Add a directional edge from vertex a to vertex b
	 * 
	 * Assume: a and b are valid vertices within graph
	 * 
	 * @param a tail vertex
	 * @param b head vertex
	 */
	private void addEdge(int a, int b) {
		vertices[a].add(b);
	}

	/**
	 * Topological sort algorithm that utilizes Depth-first search
	 * 
	 * @param start vertex to start dfs from
	 */
	private void topo_dfs(int start) {
		visited[start] = true;
		Iterator<Integer> adjacentVertices = vertices[start].iterator();
		while (adjacentVertices.hasNext()) {
			int nextVertex = adjacentVertices.next();
			if (!visited[nextVertex])
				topo_dfs(nextVertex);
		}
		topoPath.add(start);
	}

	public static void main(String[] args) {
		TopologicalSort graph = new TopologicalSort(8);
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 5);
		graph.addEdge(7, 6);

		for (int i = 0; i < graph.vertices.length; i++) {
			if (!graph.visited[i])
				graph.topo_dfs(i);
		}
		Collections.reverse(graph.topoPath);

		System.out.println("Topological Sort: " + graph.topoPath);

	}
}