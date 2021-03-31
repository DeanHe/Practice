package twoPointers;
/*
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/
public class SortColors {
	/**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // write your code here
    	if(nums == null || nums.length <= 1){
            return;
        }
    	int len = nums.length, zero = 0, i = 0, two = len - 1;
    	while (i <= two) {
    		if(nums[i] == 0){
    			swap(nums, zero++, i++);
    		} else if(nums[i] == 2){
    			swap(nums, i, two--);
    		} else {
    			// nums[i] == 1
    			i++;
    		}
		}
    }
    private void swap(int[] nums, int i, int j){
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
}
