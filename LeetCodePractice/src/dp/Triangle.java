package dp;

import java.util.List;

/*
Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.



Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10


Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-10^4 <= triangle[i][j] <= 10^4


Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?

analysis:
bottom up dp
dp[i] means minimum sum path ended by current row and index i
initialize by the last row
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        if(rows == 1){
            return triangle.get(0).get(0);
        }
        int[] dp = new int[rows];
        //init
        for(int i = 0; i < rows; i++){
            dp[i] = triangle.get(rows - 1).get(i);
        }
        for(int r = rows - 2; r >= 0; r--){
            for(int i = 0; i <= r; i++){
                dp[i] = Math.min(dp[i], dp[i + 1]) + triangle.get(r).get(i);
            }
        }
        return dp[0];
    }
}

