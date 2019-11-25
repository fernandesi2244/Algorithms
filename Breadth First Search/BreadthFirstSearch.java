import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to represent a breadth-first search of a graph based on an
 * adjacency-list representation
 * 
 * @author Ian Fernandes
 *
 */
public class BreadthFirstSearch {

	/**
	 * Adjacency List representation of graph
	 */
	private LinkedList<Integer>[] vertices;

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
	public BreadthFirstSearch(int v) {
		vertices = new LinkedList[v];
		for (int i = 0; i < v; i++)
			vertices[i] = new LinkedList<>();
	}

	/**
	 * Add an extraverted (bidirectional) edge from vertex a to vertex b
	 * 
	 * Assume: a and b are valid vertices within graph
	 * 
	 * @param a
	 * @param b
	 */
	public void addEdge(int a, int b) {
		vertices[a].add(b);
		vertices[b].add(a);
	}

	/**
	 * Perform a breadth-first search on the graph from vertex start to vertex end
	 * 
	 * Assume: a is a valid vertex within graph
	 * 
	 * @param start vertex to start bfs from
	 * @param end   target node
	 * @return end Point representing target node
	 */
	public Point bfs(int start, int end) {
		Queue<Point> toVisit = new LinkedList<>();
		boolean[] visited = new boolean[vertices.length];

		toVisit.add(new Point(start, null));
		visited[start] = true;

		if (start == end)
			return new Point(start, null);

		while (!toVisit.isEmpty()) {
			Point p = toVisit.remove();
			Iterator<Integer> adjacentVertices = vertices[p.location].iterator();
			while (adjacentVertices.hasNext()) {
				int adjacentVertex = adjacentVertices.next();
				if (adjacentVertex == end)
					return new Point(adjacentVertex, p);
				if (!visited[adjacentVertex]) {
					visited[adjacentVertex] = true;
					toVisit.add(new Point(adjacentVertex, p));
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		BreadthFirstSearch graph = new BreadthFirstSearch(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 4);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);

		Point p = graph.bfs(0, 4);
		if (p == null) {
			System.out.println("No Path Exists!");
		} else {
			ArrayList<Integer> path = new ArrayList<>();
			while (!(p.parent == null)) {
				path.add(p.location);
				p = p.parent;
			}

			path.add(p.location);
			Collections.reverse(path);
			System.out.println("Path: " + path);
		}
	}
}
