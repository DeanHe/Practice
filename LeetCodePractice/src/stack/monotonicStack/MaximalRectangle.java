package stack.monotonicStack;

import java.util.Stack;

/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

        Example:

        Input:
        [
        ["1","0","1","0","0"],
        ["1","0","1","1","1"],
        ["1","1","1","1","1"],
        ["1","0","0","1","0"]
        ]
        Output: 6

time complexity O(n ^ 2)
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
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
                int h = height[stack.pop()];
                int w = 0;
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
            int h = height[stack.pop()];
            int w = 0;
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
