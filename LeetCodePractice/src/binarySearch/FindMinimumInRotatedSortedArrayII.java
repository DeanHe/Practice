package binarySearch;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted array.
Would allow duplicates affect the run-time complexity? How and why?

tag: divid and conquer
TC O(lgN)
 */
public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        return helper(nums, 0, len - 1);

    }

    private int helper(int[] nums, int s, int e) {
        if(s + 1 >= e){
            return Math.min(nums[s], nums[e]);
        }
        if(nums[s] < nums[e]){
            return nums[s];
        }
        int mid = s + (e - s) / 2;
        return Math.min(helper(nums, s, mid - 1), helper(nums, mid, e));
    }
}
