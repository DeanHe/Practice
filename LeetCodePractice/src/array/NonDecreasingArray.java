package array;
/*
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].

analysis:
Greedy
Whenever we encounter a violation at a particular index i
you need to think either increase nums[i] pr decrease nums[i - 1]
*/
public class NonDecreasingArray {
	public boolean checkPossibility(int[] nums) {
        if(nums == null || nums.length < 2){
            return true;
        }
        // two scenarios, 1: decrement nums[i-1]; 2: increment nums[i]
        int len = nums.length;
        int violations = 0;
        for(int i = 1; i < len; i++){
            if(nums[i - 1] > nums[i]){
                if(violations == 1){
                    return false;
                }
                violations++;
                if(i < 2 || nums[i - 2] <= nums[i]){
                  nums[i - 1] =  nums[i];
                } else {
                  nums[i] = nums[i - 1];  
                }
            }
        }
        return true;
    }
}
