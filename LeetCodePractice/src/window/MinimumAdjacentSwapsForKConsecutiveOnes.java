package window;

import java.util.ArrayList;
import java.util.List;

/*
You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's. In one move, you can choose two adjacent indices and swap their values.

Return the minimum number of moves required so that nums has k consecutive 1's.



Example 1:

Input: nums = [1,0,0,1,0,1], k = 2
Output: 1
Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
Example 2:

Input: nums = [1,0,0,0,0,0,1,1], k = 3
Output: 5
Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
Example 3:

Input: nums = [1,1,0,1], k = 2
Output: 0
Explanation: nums already has 2 consecutive 1's.


Constraints:

1 <= nums.length <= 10^5
nums[i] is 0 or 1.
1 <= k <= sum(nums)

analysis:
https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/discuss/987607/O(n)-explanation-with-picture

time complexity: O(N)
 */
public class MinimumAdjacentSwapsForKConsecutiveOnes {
    public int minMoves(int[] nums, int k) {
        int res = Integer.MAX_VALUE;
        List<Integer> ones = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                ones.add(i);
            }
        }
        int len = ones.size();
        List<Integer> preSum = new ArrayList<>();
        preSum.add(0);
        for(int i = 0; i < len; i++){
            preSum.add(preSum.get(i) + ones.get(i));
        }
        for(int i = 0; i < len - k + 1; i++){
            int rightSum = preSum.get(i + k) - preSum.get(i + (k + 1) / 2);
            int leftSum = preSum.get(i + k / 2) - preSum.get(i);
            res = Math.min(res, rightSum - leftSum);
        }
        res -= (k / 2) * ((k + 1) / 2);
        return res;
    }
}
