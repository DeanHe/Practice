package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.

A uni-value grid is a grid where all the elements of it are equal.

Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.



Example 1:


Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following:
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.
Example 2:


Input: grid = [[1,5],[2,3]], x = 1
Output: 5
Explanation: We can make every element equal to 3.
Example 3:


Input: grid = [[1,2],[3,4]], x = 2
Output: -1
Explanation: It is impossible to make every element equal.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10^5
1 <= m * n <= 10^5
1 <= x, grid[i][j] <= 10^4

hint:
1 Is it possible to make two integers a and b equal if they have different remainders dividing by x?
2 If it is possible, which number should you select to minimize the number of operations?
3 What if the elements are sorted?
 */
public class MinimumOperationsToMakeaUniValueGrid {
    public int minOperations(int[][] grid, int x) {
        List<Integer> ls = new ArrayList<>();
        for(int[] arr : grid){
            for(int n : arr){
                ls.add(n);
            }
        }
        Collections.sort(ls);
        int median = ls.get((ls.size() - 1) / 2);
        int res = 0;
        for(int n : ls){
            int diff = Math.abs(median - n);
            if(diff % x != 0){
                return -1;
            }
            res += diff / x;
        }
        return res;
    }
}
