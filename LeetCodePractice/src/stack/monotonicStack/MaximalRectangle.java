package stack.monotonicStack;

import java.util.Stack;

/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
Input: matrix = [
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"]
]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:
Input: matrix = [["0"]]
Output: 0

Example 3:
Input: matrix = [["1"]]
Output: 1


Constraints:
rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.

analysis:
stack maintains a monotonic increasing order, for all heights insides stack >= height[i], calculate the h and w -> area.
TC: O(n ^ 2)
SC: O(n)
*/
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length, res = 0;
        int[] height = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    height[c]++;
                } else {
                    height[c] = 0;
                }
            }
            res = Math.max(res, calMaxArea(height));
        }
        return res;
    }

    private int calMaxArea(int[] height) {
        int res = 0, len = height.length;
        Stack<Integer> stack = new Stack<>();
        int h, w;
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
                h = height[stack.pop()];
                if(stack.isEmpty()){
                    w = i;
                } else {
                    w = i - stack.peek() - 1;
                }
                res = Math.max(res, h * w);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            h = height[stack.pop()];
            if (stack.isEmpty()) {
                w = len;
            } else {
                w = len - stack.peek() - 1;
            }
            res = Math.max(res, h * w);
        }
        return res;
    }
}
