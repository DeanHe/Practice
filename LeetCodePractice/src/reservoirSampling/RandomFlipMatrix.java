package reservoirSampling;

import java.util.*;

/*You are given the number of rows n_rows and number of columns n_cols of a 2D binary matrix where all values are initially 0. Write a function flip which chooses a 0 value uniformly at random, changes it to 1, and then returns the position [row.id, col.id] of that value. Also, write a function reset which sets all values back to 0. Try to minimize the number of calls to system's math.random() and optimize the time and space complexity.

Note:

1 <= n_rows, n_cols <= 10000
0 <= row.id < n_rows and 0 <= col.id < n_cols
flip will not be called when the matrix has no 0 values left.
the total number of calls to flip and reset will not exceed 1000.
Example 1:

Input: 
["Solution","flip","flip","flip","flip"]
[[2,3],[],[],[],[]]
Output: [null,[0,1],[1,2],[1,0],[1,1]]
Example 2:

Input: 
["Solution","flip","flip","reset","flip"]
[[1,2],[],[],[],[]]
Output: [null,[0,0],[0,1],null,[0,0]]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, n_rows and n_cols. flip and reset have no arguments. Arguments are always wrapped with a list, even if there aren't any.

analysis:
every repeated index map to pick total (last index)
*/
public class RandomFlipMatrix {
	int rows, cols, total;
	Random random;
	Map<Integer, Integer> map;

	public RandomFlipMatrix(int n_rows, int n_cols) {
		random = new Random();
		rows = n_rows;
		cols = n_cols;
		total = rows * cols;
		// random pick : its fall back value if conflict
		map = new HashMap<>();
	}

	public int[] flip() {
		int rand = random.nextInt(total);
		total--;
		int idx = map.getOrDefault(rand, rand);
		map.put(rand, map.getOrDefault(total, total));
		return new int[] { idx / cols, idx % cols };
	}

	public void reset() {
		total = rows * cols;
		map.clear();
	}
}
/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(n_rows, n_cols); int[] param_1 = obj.flip(); obj.reset();
 */
