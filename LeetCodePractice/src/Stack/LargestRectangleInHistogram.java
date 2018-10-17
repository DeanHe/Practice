package Stack;

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
           int curHeight = height[i];
           if(stack.isEmpty() || height[stack.peek()] <= curHeight){
               stack.push(i);
               i++;
           } else {
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
