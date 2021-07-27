package monotonicQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/*
#503

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

analysis:
loop from 0 -> 2 * len
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
    	Deque<Integer> deque = new ArrayDeque<>();
		Arrays.fill(res, -1);
		for(int i = 0; i < len * 2; i++){
			int cur = i % len;
			while(!deque.isEmpty() && nums[deque.peekLast()] < nums[cur]){
				int pre = deque.pollLast();
				res[pre] = nums[cur];
			}
			deque.offerLast(cur);
		}
    	return res;
    }
}
