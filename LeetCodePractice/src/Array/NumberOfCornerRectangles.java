package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
#750
Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

Example 1:

Input: grid =
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
Example 2:

Input: grid =
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
Example 3:

Input: grid =
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
Note:

The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.

analysis:
two rows scan, count how many ones at the same column
then C(N, 2) is the count of rectangles formed by these two rows
 */
public class NumberOfCornerRectangles {
    /**
     * @param grid: the grid
     * @return: the number of corner rectangles
     */
    public int countCornerRectangles(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, res = 0;
        for(int r1 = 0; r1 < rows; r1++){
            List<Integer> ones = new ArrayList<>();
            for(int c = 0; c < cols; c++){
                if(grid[r1][c] == 1){
                    ones.add(c);
                }
            }
            for(int r2 = r1 + 1; r2 < rows; r2++){
                int cnt = 0;
                for(int c : ones){
                    if(grid[r2][c] == 1){
                        cnt++;
                    }
                }
                res += cnt * (cnt - 1) / 2;
            }
        }
        return res;
    }

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int[] preSum = new int[candiesCount.length + 1];
        for(int i = 0; i + 1 < preSum.length; i++){
            preSum[i + 1] = preSum[i] + candiesCount[i];
        }
        boolean[] res = new boolean[queries.length];
        for(int i = 0; i < res.length; i++){
            res[i] = dfs(preSum, queries[i]);
        }
        return res;
    }

    private boolean dfs(int[] preSum, int[] query){
        int type = query[0];
        int day = query[1];
        int cap = query[2];
        int least = preSum[type] / cap + 1, most = preSum[type + 1];
        System.out.println("least:" + least + "most:" + most);
        if(day < least){
            return false;
        }
        if(day >= most){
            return false;
        }
        return true;
    }
}
