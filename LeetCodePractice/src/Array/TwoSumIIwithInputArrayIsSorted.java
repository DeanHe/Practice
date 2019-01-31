package Array;
/*Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Given nums = [2, 7, 11, 15], target = 9
return [1, 2]*/
public class TwoSumIIwithInputArrayIsSorted {
	/**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        // write your code here
    	int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }
        int len = nums.length;
        int start = 0, end = len - 1;
        while(start < end){
        	int sum = nums[start] + nums[end];
        	if(sum == target){
        		res[0] = start;
        		res[1] = end;
        		break;
        	} else if (sum < target) {
				start++;
			} else {
				end--;
			}
        }
        return res;
    }
}
