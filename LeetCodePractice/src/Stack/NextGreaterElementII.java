package Stack;

import java.util.Stack;

/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example
Example 1:

Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: [1]
Output: [-1]
Explanation: 
The number 1 can't find next greater number.
Notice
The length of given array won't exceed 10000.

time complexity O(n)
*/
public class NextGreaterElementII {
	/**
     * @param nums: an array
     * @return: the Next Greater Number for every element
     */
    public int[] nextGreaterElements(int[] nums) {
    	int len = nums.length;
    	int[] res = new int[len]; 
    	Stack<Integer> stack = new Stack<>();
    	for(int i = len - 1; i >= 0; i--){
    		stack.push(i);
    	}
    	for(int i = len - 1; i >= 0; i--){
    		res[i] = -1;
    		while(!stack.isEmpty() && nums[i] >= nums[stack.peek()]){
    			stack.pop();
    		}
    		if(!stack.isEmpty()){
    			res[i] = nums[stack.peek()];
    		}
    		stack.add(i);
    	}
    	return res;
    }
}
