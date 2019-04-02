package TwoPointers;
/*Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example
Example 1:
	Input:  nums = [1,0,1,1,0]
	Output:  4
	
	Explanation:
	Flip the first zero will get the the maximum number of consecutive 1s.
	After flipping, the maximum number of consecutive 1s is 4.

Example 2:
	Input: nums = [1,0,1,0,1]
	Output:  3
	
	Explanation:
	Flip each zero will get the the maximum number of consecutive 1s.
	After flipping, the maximum number of consecutive 1s is 3.
	
Notice
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000.*/
public class MaxConsecutiveOnesII {
	/**
     * @param nums: a list of integer
     * @return: return a integer, denote  the maximum number of consecutive 1s
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        // write your code here
    	int res = 0, savedOnes = 0, ones = 0;
    	for(int i = 0; i < nums.length; i++){
    		ones++;
    		if(nums[i] == 0){
    			savedOnes = ones;
    			ones = 0;
    		}
    		res = Math.max(res, savedOnes + ones);
    	}
    	return res;
    }
}
