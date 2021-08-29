package binarySearch;
/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0

Constraints:

1 <= nums.length <= 10^4
-10^4 <= nums[i] <= 10^4
nums contains distinct values sorted in ascending order.
-10^4 <= target <= 10^4
*/
public class SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(nums[mid] > target){
                end = mid - 1;
            } else if(nums[mid] == target){
                return mid;
            } else {
                //nums[mid] < target
                start = mid + 1;
            }
        }
        return start;
    }
}
