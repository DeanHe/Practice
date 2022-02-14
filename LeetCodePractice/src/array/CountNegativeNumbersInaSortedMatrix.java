package array;
/*
Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

Example 1:
Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.

Example 2:
Input: grid = [[3,2],[1,0]]
Output: 0

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
-100 <= grid[i][j] <= 100


Follow up: Could you find an O(n + m) solution?
 */
public class CountNegativeNumbersInaSortedMatrix {
    public int countNegatives(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, res = 0;
        int r = rows - 1, c = 0;
        while(r >= 0 && c < cols){
            if(grid[r][c] < 0){
                res += cols - c;
                r--;
            } else {
                c++;
            }
        }
        return res;
    }
}
