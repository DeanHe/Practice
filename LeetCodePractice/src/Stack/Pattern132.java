package Stack;

import java.util.Stack;

/*Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]
Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]
Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]
Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
*/
public class Pattern132 {
	public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i = nums.length - 1; i >= 0; i--){
        	while(!stack.isEmpty() && nums[i] > stack.peek()) {
                max = stack.pop();
            }
        	if(nums[i] > max){
        	    stack.push(nums[i]);
            }
        	//@1 < @2
        	if(nums[i] < max){
        	    return true;
            }
        }
        return false;
    }
}
