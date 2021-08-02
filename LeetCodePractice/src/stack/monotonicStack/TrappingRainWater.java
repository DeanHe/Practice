package stack.monotonicStack;

import java.util.Stack;

/*
#42
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9


Constraints:

n == height.length
0 <= n <= 3 * 10^4
0 <= height[i] <= 10^5

analysis:
way 1: stack, maintain a monotonic decreasing stack
two pointer solution: always move the pointer on the shorter building side
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int res = 0, len = height.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int bot = height[stack.pop()];
                if (!stack.isEmpty()) {
                    //compare bot bar left and right height
                    int h = Math.min(height[stack.peek()], height[i]) - bot;
                    int w = i - stack.peek() - 1;
                    res += h * w;
                }
            }
            stack.push(i);
        }
        return res;
    }

    //space O(1)
    public int trapII(int[] height) {
        int res = 0, len = height.length;
        int s = 0, e = len - 1, maxLeft = 0, maxRight = 0;
        while (s < e) {
            if (height[s] < height[e]) {
                if (height[s] >= maxLeft) {
                    maxLeft = height[s];
                } else {
                    res += maxLeft - height[s];
                }
                s++;
            } else {
                if (height[e] >= maxRight) {
                    maxRight = height[e];
                } else {
                    res += maxRight - height[e];
                }
                e--;
            }
        }
        return res;
    }
}
