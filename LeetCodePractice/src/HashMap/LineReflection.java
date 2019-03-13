package HashMap;

import java.util.*;

/*Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example
Example1

Input: [[1,1],[-1,1]]
Output: true
Example2

Input: [[1,1],[-1,-1]]
Output: false
Challenge
Could you do better than O(n2)?*/
public class LineReflection {
	/**
	 * @param points:
	 *            n points on a 2D plane
	 * @return: if there is such a line parallel to y-axis that reflect the
	 *          given points
	 */
	public boolean isReflected(int[][] points) {
		// Write your code here
		HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
		int len = points.length;
		int xmin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			int x = points[i][0];
			int y = points[i][1];
			xmin = Math.min(xmin, x);
			xmax = Math.max(xmax, x);
			if (!map.containsKey(x)) {
				map.put(x, new HashSet<Integer>());
			}
			map.get(x).add(y);
		}
		double axis = (xmax + xmin) / 2.0;
		for (int i = 0; i < len; i++) {
			int x = points[i][0];
			int y = points[i][1];
			int x_r = (int) (2 * axis - x);
			if (!map.containsKey(x_r) || !map.get(x_r).contains(y)) {
				return false;
			}
		}
		return true;
	}
}
