package array;
/*
Given an unsorted integer array, find the first missing positive integer.

Example
Example 1:

Input:[1,2,0]
Output:3
Example 2:

Input:[3,4,-1,1]
Output:2
Challenge
Your algorithm should run in O(n) time and uses constant space.
*/
public class FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
		if(nums == null || nums.length == 0){
            return 1;
        }
        int len = nums.length;
        // 1. mark numbers (num <= 0) and (num > n) with a special marker number (len+1)
        // (we can ignore those because if all number are > n then we'll simply return 1)
		for(int i = 0; i < len; i++){
		    if(nums[i] <= 0 || nums[i] > len){
                nums[i] = len + 1;
            }
        }
        // note: all number in the array are now positive, and on the range 1..len+1
        // 2. mark each cell appearing in the array, by converting the index for that number to negative
        for (int i = 0; i < len; i++) {
            int n = Math.abs(nums[i]);
            if(n > len){
                continue;
            }
            n--; // -1 for zero index based array (so the number 1 will be at pos 0)
            if(nums[n] > 0){
                nums[n] = -1 * nums[n];
            }
        }
        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        // 4. no positive numbers were found, which means the array contains all numbers 1..n
        return len + 1;
	}
}
