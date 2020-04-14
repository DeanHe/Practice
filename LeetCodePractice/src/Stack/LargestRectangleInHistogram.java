package Stack;
/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
find the area of largest rectangle in the histogram.

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
       if(height == null || height.length == 0){
           return 0;
       }
       int len = height.length;
       int maxRect = 0;
       //stack of index
       Stack<Integer> stack = new Stack<>();
       int i = 0;
       while(i < len){
           // If this bar is higher than the bar on top stack, push its idx to stack
           if(stack.isEmpty() || height[stack.peek()] <= height[i]){
               stack.push(i);
               i++;
           } else {
               // If this bar is lower than top of stack, then calculate area of rectangle
               // with stack top idx bar as the rectangle height. i as the rectangle right bound, next element in stack as left bound
               int index = stack.pop();
               int h = height[index];
               int w = 0;
               if(stack.isEmpty()){
                   w = i; // all bars before i are lower than height
               } else {
                   w = i - stack.peek() - 1;
               }
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
