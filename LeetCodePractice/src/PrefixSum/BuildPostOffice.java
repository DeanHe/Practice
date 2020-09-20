package PrefixSum;

import java.util.ArrayList;
import java.util.Collections;

/*
Given a 2D grid, each cell is either an house 1 or empty 0 (the number zero, one), find the place to build a post office, the distance that post office to all the house sum is smallest. Return the smallest distance. Return -1 if it is not possible.

Example
Given a grid:

0 1 0 0
1 0 1 1
0 1 0 0
return 6. (Placing a post office at (1,1), the distance that post office to all the house sum is smallest.)

Notice
You can pass through house and empty.
You only build post office on an empty.
*/
public class BuildPostOffice {
	/**
	 * @param grid:
	 *            a 2D grid
	 * @return: An integer
	 */
	public int shortestDistance(int[][] grid) {
		// write your code here
		int rows = grid.length;
		int cols = grid[0].length;
		ArrayList<Integer> houses_r = new ArrayList<>();
		ArrayList<Integer> houses_c = new ArrayList<>();
		ArrayList<Integer> preSum_r = new ArrayList<>();
		ArrayList<Integer> preSum_c = new ArrayList<>();
		int totalHouses = 0;
		int res = Integer.MAX_VALUE;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 1) {
					houses_r.add(r);
					houses_c.add(c);
					totalHouses++;
				}
			}
		}
		Collections.sort(houses_r);
		Collections.sort(houses_c);
		preSum_r.add(0);
		preSum_c.add(0);
		for (int i = 1; i <= totalHouses; i++) {
			preSum_r.add(preSum_r.get(i - 1) + houses_r.get(i - 1));
			preSum_c.add(preSum_c.get(i - 1) + houses_c.get(i - 1));
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 0) {
					int cost_r = getCost(houses_r, preSum_r, r, totalHouses);
					int cost_c = getCost(houses_c, preSum_c, c, totalHouses);
					if (cost_r + cost_c < res) {
						res = cost_r + cost_c;
					}
				}
			}
		}
		return res;
	}

	private int getCost(ArrayList<Integer> houses, ArrayList<Integer> preSum, int index, int totalHouses) {
		if (totalHouses == 0) {
			return 0;
		}
		if (houses.get(0) > index) {
			return preSum.get(totalHouses) - index * totalHouses;
		}
		int start = 0, end = totalHouses - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (houses.get(mid) <= index) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}
		int floor = 0;
		if (houses.get(end) <= index) {
			floor = end;
		} else {
			floor = start;
		}
		int leftComparison = index * (floor + 1) - preSum.get(floor + 1);
		int rightComparison = preSum.get(totalHouses) - preSum.get(floor + 1) - index * (totalHouses - (floor + 1));
		return leftComparison + rightComparison;
	}
}
