package sort;
/*Given an unsorted array nums, reorder it in-place such that

nums[0] <= nums[1] >= nums[2] <= nums[3]....
Example
Example 1:

Input: [3, 5, 2, 1, 6, 4]
Output: [1, 6, 2, 5, 3, 4]
Explanation: This question may have multiple answers, and [2, 6, 1, 5, 3, 4] is also ok.
Example 2:

Input: [1, 2, 3, 4]
Output: 1， 3， 2， 4]
Notice
Please complete the problem in-place.*/
public class WiggleSort {
	/*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        for(int i = 1; i < len; i++){
        	if((i % 2 == 1 && nums[i] < nums[i - 1])
        		|| (i % 2 == 0 && nums[i] > nums[i - 1])){
        		int temp = nums[i - 1];
        		nums[i - 1] = nums[i];
        		nums[i] = temp;
        	}
        }
    }
}
