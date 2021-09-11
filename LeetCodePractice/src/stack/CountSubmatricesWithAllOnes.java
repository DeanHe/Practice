package stack;

import java.util.Stack;

/*
Given a m * n matrix of ones and zeros, return how many sub matrices have all ones.

Example 1:

Input: mat = [[1,0,1],
              [1,1,0],
              [1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2.
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
Example 2:

Input: mat = [[0,1,1,0],
              [0,1,1,1],
              [1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3.
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2.
There are 2 rectangles of side 3x1.
There is 1 rectangle of side 3x2.
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
Example 3:

Input: mat = [[1,1,1,1,1,1]]
Output: 21
Example 4:

Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
Output: 5

        Constraints:

        1 <= arr.length <= 300
        1 <= arr[0].length <= 300
        0 <= arr[i][j] <= 1

analysis:
similar to MaximalSquare

TC: O(M*N)
*/
public class CountSubmatricesWithAllOnes {
    public int countMatricies(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length, count = 0;
        int[] height = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1) {
                    height[c]++;
                } else {
                    height[c] = 0;
                }
            }
            count += helper(height);
        }
        return count;
    }

    public int helper(int[] heights) {
        //System.out.println(Arrays.toString(heights));
        int[] sum = new int[heights.length]; // sum[i] means count of matrix formed with right edge on idx height[i]
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int c = 0; c < heights.length; c++) {
            while (!stack.isEmpty()) {
                if (heights[stack.peek()] >= heights[c]) {
                    stack.pop();
                } else {
                    break;
                }
            }
            if(!stack.isEmpty()){
                int preIdx = stack.peek();
                sum[c] = sum[preIdx];
                sum[c] += heights[c] * (c - preIdx);
            } else {
                sum[c] = heights[c] * (c + 1);
            }
            stack.push(c);
        }
        for(int n : sum){
            res += n;
        }
        return res;
    }
}
