package PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.



Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109

analysis:
find head and tail segment sums to x is equal to find middle segment sums to total - x
to minimize the operations is same as find the longest middle segment sums to total - x;
 */
public class MinimumOperationsToReduceXtoZero {
    public int minOperations(int[] nums, int x) {
        int target = -x;
        for (int n : nums) {
            target += n;
        }
        if(target == 0){
            return nums.length;
        }
        int sum = 0;
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> preSumIdx = new HashMap<>();
        preSumIdx.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSumIdx.containsKey(sum - target)) {
                res = Math.max(res, i - preSumIdx.get(sum - target));
            }
            preSumIdx.putIfAbsent(sum, i);
        }
        return res == Integer.MIN_VALUE ? -1 : nums.length - res;
    }
}
