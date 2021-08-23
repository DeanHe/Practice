package contest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/*
You are given an m x n integer matrix mat and an integer target.

Choose one integer from each row in the matrix such that the absolute difference between target and the sum of the chosen elements is minimized.

Return the minimum absolute difference.

The absolute difference between two numbers a and b is the absolute value of a - b.



Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
Output: 0
Explanation: One possible choice is to:
- Choose 1 from the first row.
- Choose 5 from the second row.
- Choose 7 from the third row.
The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
Example 2:


Input: mat = [[1],[2],[3]], target = 100
Output: 94
Explanation: The best possible choice is to:
- Choose 1 from the first row.
- Choose 2 from the second row.
- Choose 3 from the third row.
The sum of the chosen elements is 6, and the absolute difference is 94.
Example 3:


Input: mat = [[1,2,9,8,7]], target = 6
Output: 1
Explanation: The best choice is to choose 7 from the first row.
The absolute difference is 1.


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 70
1 <= mat[i][j] <= 70
1 <= target <= 800

hint:
The sum of chosen elements will not be too large. Consider using a hash set to record all possible sums while iterating each row.
Instead of keeping track of all possible sums, since in each row, we are adding positive numbers, only keep those that can be a candidate, not exceeding the target by too much.

analysis:
dp[i][j] means the minimum difference by arriving at row i with sum j
 */
public class MinimizeTheDifferenceBetweenTargetAndChosenElements {
    public int minimizeTheDifference(int[][] mat, int target) {
        int rows = mat.length, cols = mat[0].length;
        Integer[][] dp = new Integer[rows][70 * 70 + 1];
        List<TreeSet<Integer>> g = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            TreeSet<Integer> set = new TreeSet<>();
            for (int c = 0; c < cols; c++) {
                set.add(mat[r][c]);
            }
            g.add(set);
        }
        return dfs(g, dp, 0, 0, target);
    }

    private int dfs(List<TreeSet<Integer>> g, Integer[][] dp, int r, int sum, int target) {
        int rows = g.size();
        if (r == rows) {
            return Math.abs(sum - target);
        }
        if (dp[r][sum] != null) {
            return dp[r][sum];
        }
        int res = Integer.MAX_VALUE;
        for (int n : g.get(r)) {
            int diff = dfs(g, dp, r + 1, sum + n, target);
            if (res > diff) {
                res = diff;
            } else {
                break;
            }
        }
        return dp[r][sum] = res;
    }
}

