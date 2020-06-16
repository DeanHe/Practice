package Stack;

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
    int area = 0;

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[] height = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') {
                    height[c]++;
                } else {
                    height[c] = 0;
                }
            }
            calMaxArea(height);
        }
        return area;
    }

    private void calMaxArea(int[] height) {
        int c = 0, cols = height.length;
        Stack<Integer> stack = new Stack<>();
        while (c < cols) {
            if (stack.empty() || height[stack.peek()] < height[c]) {
                stack.push(c);
                c++;
            } else {
                int idx = stack.pop();
                int h = height[idx];
                int w = 0;
                if (stack.isEmpty()) {
                    w = c;
                } else {
                    w = c - stack.peek() - 1;
                }
                area = Math.max(area, h * w);
            }
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int h = height[idx];
            int w = 0;
            if (stack.isEmpty()) {
                w = c;
            } else {
                w = c - stack.peek() - 1;
            }
            area = Math.max(area, h * w);
        }
    }
}
