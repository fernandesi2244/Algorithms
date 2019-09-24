import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class DepthFirstSearch {
	
	LinkedList<Integer>[] vertices;
	boolean[] visited;
	
	@SuppressWarnings("unchecked")
	public DepthFirstSearch(int v) {
		vertices = new LinkedList[v];
		for(int i = 0;i<vertices.length; i++) {
			vertices[i] = new LinkedList<>();
		}
		visited = new boolean[v];
	}
	
	/**
	 * Precondition: a and b are between 0 and v-1
	 * @param a
	 * @param b
	 */
	public void addEdge(int a, int b) {
		vertices[a].add(b);
		vertices[b].add(a); //undirected graph
	}
	
	/**
	 * Precondition: a is between 0 and v-1
	 * @param a
	 */
	public void dfs(int a) {
		visited[a] = true;
		System.out.println(a);
		
		Iterator<Integer> it = vertices[a].iterator();
		while(it.hasNext()) {
			int n = it.next();
			if(!visited[n])
				dfs(n);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many vertices do you want in the graph?");
		DepthFirstSearch g = new DepthFirstSearch(Integer.parseInt(scan.nextLine()));
		System.out.println("List out pairs of vertices that you want to be connected on separate lines with spaces between them. "
				+ "Click enter after each pair. "
				+ "Each pair that you list will result in an edge being established between those two points. "
				+ "Make sure that you only list numbers from 0 to v-1.");
		System.out.println("Enter stop to perform depth-first traversal of graph.");
		String next = scan.nextLine();
		while(!next.equalsIgnoreCase("stop")) {
			int a = Integer.parseInt(next.split(" ")[0]);
			int b = Integer.parseInt(next.split(" ")[1]);
			g.addEdge(a, b);
			next = scan.nextLine();
		}
		
		System.out.println("Which vertex (number) do you want to start on? (Click enter once done)");
		g.dfs(scan.nextInt());
		
		scan.close();
	}

}
