package Stack.MonotonicStack;
/*
#84
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.

        Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

        The largest rectangle is shown in the shaded area, which has area = 10 unit.

        Example:

        Input: [2,1,5,6,2,3]
        Output: 10

analysis:
maintain monotonic increasing heights stack
TC: O(N)
*/

import java.util.Stack;

public class LargestRectangleInHistogram {
	/**
    * @param heights: A list of integer
    * @return: The area of largest rectangle in the histogram
    */
   public int largestRectangleArea(int[] heights) {
       if (heights == null || heights.length == 0) {
           return 0;
       }
       int len = heights.length, maxRect = 0;
       //stack of index
       Stack<Integer> stack = new Stack<>();
       for (int i = 0; i < len; i++) {
           while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
               int h = heights[stack.pop()];
               int w = 0;
               if(!stack.isEmpty()){
                   w = i - stack.peek() - 1;
               } else {
                   w = i;
               }
               maxRect = Math.max(h * w, maxRect);
           }
           stack.push(i);
       }
       while (!stack.isEmpty()) {
           int h = heights[stack.pop()];
           int w = 0;
           if(!stack.isEmpty()){
               w =  len - stack.peek() - 1;
           } else {
               w = len;
           }
           maxRect = Math.max(h * w, maxRect);
       }
       return maxRect;
   }
}
