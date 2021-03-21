package array;
/*
#581

Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
Return the shortest such subarray and output its length.



Example 1:
Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

Example 2:
Input: nums = [1,2,3,4]
Output: 0

Example 3:
Input: nums = [1]
Output: 0


Constraints:

1 <= nums.length <= 10^4
-10^5 <= nums[i] <= 10^5
 */
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length, s = -1, e = -1, most = nums[0], least = nums[len - 1];
        for(int i = 1; i < len; i++){
            most = Math.max(most, nums[i]);
            least = Math.min(least, nums[len - 1 - i]);
            if(nums[i] < most){
                e = i;
            }
            if(nums[len - 1 - i] > least){
                s = len - 1 - i;
            }
        }
        if(e == -1){
            return 0;
        }
        return e - s + 1;
    }
}
