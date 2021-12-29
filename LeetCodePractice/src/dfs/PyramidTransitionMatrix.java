package dfs;

import java.util.*;

/*
We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  D   E
 / \ / \
X   Y   Z

This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
Example 2:
Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
Note:
bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

*/
public class PyramidTransitionMatrix {
	Map<String, List<String>> map = new HashMap<>();
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		for (String s : allowed) {
			String key = s.substring(0, 2);
			String value = s.substring(2);
			if (!map.containsKey(key)) {
				List<String> ls = new ArrayList<>();
				map.put(key, ls);
			}
			map.get(key).add(value);
		}
		return helper(bottom);
	}

	private boolean helper(String level) {
		if (level.length() == 1) {
			return true;
		}
		for (int i = 0; i < level.length() - 1; i++) {
			if (!map.containsKey(level.substring(i, i + 2))) {
				return false;
			}
		}
		ArrayList<String> candidates = new ArrayList<>();
		getAllNextLevels(level, new StringBuilder(), candidates);
		for (String candidate : candidates) {
			if (helper(candidate)) {
				return true;
			}
		}
		return false;
	}

	private void getAllNextLevels(String level, StringBuilder sb, ArrayList<String> candidates) {
		if (level.length() == 1) {
			candidates.add(sb.toString());
			return;
		}
		String key = level.substring(0, 2);
		if (map.containsKey(key)) {
			List<String> vals = map.get(key);
			for (String transition : vals) {
				sb.append(transition);
				getAllNextLevels(level.substring(1), sb, candidates);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}
