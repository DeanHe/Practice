package contest;

import java.util.Arrays;

/*
You are given an integer array nums. In one operation, you can replace any element in nums with any integer.

nums is considered continuous if both of the following conditions are fulfilled:

All elements in nums are unique.
The difference between the maximum element and the minimum element in nums equals nums.length - 1.
For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.

Return the minimum number of operations to make nums continuous.



Example 1:

Input: nums = [4,2,5,3]
Output: 0
Explanation: nums is already continuous.
Example 2:

Input: nums = [1,2,3,5,6]
Output: 1
Explanation: One possible solution is to change the last element to 4.
The resulting array is [1,2,3,5,4], which is continuous.
Example 3:

Input: nums = [1,10,100,1000]
Output: 3
Explanation: One possible solution is to:
- Change the second element to 2.
- Change the third element to 3.
- Change the fourth element to 4.
The resulting array is [1,2,3,4], which is continuous.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9

hint:
1 Sort the array.
2 For every index do a binary search to get the possible right end of the window and calculate the possible answer.

analysis:

sliding window

Intuition: Sort and only keep unique elements. The problem is the same as "get the length of the longest subarray whose difference between min and max elements is N - 1".
 */
public class MinimumNumberOfOperationsToMakeArrayContinuous {
    public int minOperations(int[] nums) {
        int len = nums.length;
        if(len == 1){
            return 0;
        }
        Arrays.sort(nums);
        int unique = 1;
        for(int i = 1; i < len; i++){
            if(nums[i] != nums[i - 1]){
                nums[unique++] = nums[i];
            }
        }
        int j = 0, res = len;
        for(int i = 0; i < unique; i++){
            int end = nums[i] + len - 1;
            //let `j` point to the first element that is out of range -- `> A[i] + N - 1`
            while (j < unique && nums[j] <= end){
                j++;
            }
            // The length of this subarray is `j - i`. We need to replace `len - j + i` elements to make it continuous.
            res = Math.min(res, len - j + i);
        }
        return res;
    }
}

