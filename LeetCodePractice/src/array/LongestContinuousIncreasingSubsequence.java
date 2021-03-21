package array;
/*Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:

Can be from right to left or from left to right.
Indices of the integers in the subsequence should be continuous.
Example
Example 1:

Input: [5, 4, 2, 1, 3]
Output: 4
Explanation:
For [5, 4, 2, 1, 3], the LICS  is [5, 4, 2, 1], return 4.
Example 2:

Input: [5, 1, 2, 3, 4]
Output: 4
Explanation:
For [5, 1, 2, 3, 4], the LICS  is [1, 2, 3, 4], return 4.
Challenge
O(n) time and O(1) extra space.*/
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
    	if(nums == null || nums.length == 0){
    		return 0;
    	}
        int res = 0;
        int len = nums.length;
        int temp = 1;
        for(int i = 0; i < len - 1; i++){
        	if(nums[i] < nums[i + 1]){
        		temp++;
        	} else {
        		res = Math.max(res, temp);
        		temp = 1;
        	}
        }
        temp = 1;
        for(int i = len - 1; i > 0; i--){
        	if(nums[i] < nums[i - 1]){
        		temp++;
        	} else {
        		res = Math.max(res, temp);
        		temp = 1;
        	}
        }
        return res;
    }
}
