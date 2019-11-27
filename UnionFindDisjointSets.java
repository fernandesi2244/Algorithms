import java.util.HashSet;
import java.util.Set;

public class UnionFindDisjointSets {

	/**
	 * Element at each index indicates the index of the parent element.
	 */
	private static int[] parents;

	/*
	 * Represents upper bounds on height of tree at each index
	 */
	private static int[] rank;

	/**
	 * Sets all parents array values to their respective indices, indicating that
	 * each element is the parent of itself at the beginning
	 */
	private static void initializeParents() {
		for (int i = 0; i < parents.length; i++)
			parents[i] = i;
	}

	/**
	 * Quickly and recursively finds the representative member of the set of element
	 * a. Also uses path compression heuristic
	 * 
	 * @param a the set element to find the set representative of
	 * @return the index of the set representative
	 */
	private static int findSet(int a) {
		return parents[a] == a ? a : (parents[a] = findSet(parents[a]));
	}

	/**
	 * Determine if elements a and b are in the same set.
	 * 
	 * @param a the first element
	 * @param b the second element
	 * @return whether a and b are in the same set
	 */
	private static boolean sameSet(int a, int b) {
		return findSet(a) == findSet(b);
	}

	/**
	 * Combine the sets that contain a and b
	 * 
	 * @param a first element
	 * @param b second element
	 */
	private static void unionSet(int a, int b) {
		if (!sameSet(a, b)) {
			int aRepresentative = findSet(a), bRepresentative = findSet(b);

			// Union by rank heuristic (tries to keep tree heights at minimum)
			if (rank[aRepresentative] > rank[bRepresentative]) {
				parents[bRepresentative] = aRepresentative;
			} else {
				parents[aRepresentative] = bRepresentative;
				if (rank[aRepresentative] == rank[bRepresentative])
					rank[bRepresentative]++;
			}
		}
	}

	/**
	 * Find the total number of disjoint sets
	 * 
	 * @return number of disjoint sets
	 */
	private static int numDisjointSets() {
		Set<Integer> representativeElements = new HashSet<>();
		for (int i = 0; i < parents.length; i++)
			representativeElements.add(findSet(i));
		return representativeElements.size();
	}

	/**
	 * Find the size of the set that contains element a
	 * 
	 * @param a element of set to find size of
	 * @return number of elements in the same set as a
	 */
	private static int sizeOfSet(int a) {
		int representativeElement = findSet(a);
		int no = 0;
		for (int i = 0; i < parents.length; i++)
			if (findSet(i) == representativeElement)
				no++;
		return no;
	}

	/**
	 * Test ufds methods above
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int v = 5;
		parents = new int[v];
		rank = new int[v];

		initializeParents();
		unionSet(0, 1);
		unionSet(2, 3);
		unionSet(4, 3);

		System.out.println(findSet(0));
		System.out.println(findSet(4));
		System.out.println(sameSet(2, 4));
		System.out.println(sameSet(2, 0));
		System.out.println(numDisjointSets());
		System.out.println(sizeOfSet(0));

		System.out.println();
		unionSet(0, 3);

		System.out.println(sameSet(2, 0));
		System.out.println(numDisjointSets());
		System.out.println(sizeOfSet(0));

	}

}
