import java.util.Set;
import java.util.TreeSet;

/**
 * Class to represent obtaining the permutations of a string that have the same
 * length as the original String.
 * 
 * Obtaining only x length permutations is an easy task that can be accomplished
 * by adding the first x letters of each item in the set returned by permute()
 * to a HashSet.
 * 
 * @author Ian Fernandes
 *
 */
public class StringPermutations {

	/**
	 * Generate all permutations of the input string in alphabetical order
	 * 
	 * @param input the string to permute
	 * @return the ordered set containing all permutations of input
	 */
	private static Set<String> permute(String input) {
		Set<String> set = new TreeSet<>();
		set.add(input);
		if (input.length() == 1)
			return set;
		for (int i = 0; i < input.length(); i++) {
			String remaining = input.substring(0, i) + input.substring(i + 1);
			for (String a : permute(remaining)) {
				set.add(input.charAt(i) + a);
			}
		}
		return set;
	}

	public static void main(String[] args) {
		String input = "abcd";
		Set<String> permutations = permute(input);
		permutations.forEach(x -> System.out.println(x));

	}

}
