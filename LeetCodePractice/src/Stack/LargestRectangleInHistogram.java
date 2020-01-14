package Stack;
/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

        Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

        The largest rectangle is shown in the shaded area, which has area = 10 unit.

        Example:

        Input: [2,1,5,6,2,3]
        Output: 10
*/
import java.util.*;

public class LargestRectangleInHistogram {
	/**
    * @param height: A list of integer
    * @return: The area of largest rectangle in the histogram
    */
   public int largestRectangleArea(int[] height) {
       // write your code here
       if(height == null || height.length == 0){
           return 0;
       }
       int len = height.length;
       int maxRect = 0;
       //stack of index
       Stack<Integer> stack = new Stack<>();
       int i = 0;
       while(i < len){
           // If this bar is higher than the bar on top stack, push it to stack
           if(stack.isEmpty() || height[stack.peek()] <= height[i]){
               stack.push(i);
               i++;
           } else {
               // If this bar is lower than top of stack, then calculate area of rectangle
               // with stack top as the minimum height bar. 'i' is
               // 'right bound' and element before top in stack is 'left bound'
               int index = stack.pop();
               int h = height[index];
               int w = 0;
               if(stack.isEmpty()){
                   w = i;
               } else {
                   w = i - stack.peek() - 1;
               }
               // Calculate the area with h as smallest bar
               maxRect = Math.max(h * w, maxRect);
           }
       }
       while(!stack.isEmpty()){
          int index = stack.pop();
          int h = height[index];
          int w = 0;
          if(stack.isEmpty()){
               w = i;
           } else {
               w = i - stack.peek() - 1;
           }
           maxRect = Math.max(h * w, maxRect);
       }
       return maxRect;
   }
}
