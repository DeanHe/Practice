package math;

import java.util.Arrays;

/*
Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
After this process, we have some array B.
Return the smallest possible difference between the maximum value of B and the minimum value of B.

Example 1:
Input: A = [1], K = 0
Output: 0
Explanation: B = [1]

Example 2:
Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]

Example 3:
Input: A = [1,3,6], K = 3
Output: 3
Explanation: B = [4,6,3]

Note:
1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= k <= 10000

analysis:
the answer is in form (A[0]+k, A[1]+k, ..., A[i]+k, A[i+1]-k, ..., A[n]-k)
need to find the optimal breaking point i, where on left side every number + k, on the right side every number - k
on the left side: max is A[i] + k, min is A[0] + k
on the right side: max is A[len - 1] - k, min is A[i + 1] - k
TC: O(NlogN)
 */
public class SmallestRangeII {
    public int smallestRangeII(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        int res = nums[len - 1] - nums[0];
        for (int i = 0; i + 1 < len; i++) {
            int most = Math.max(nums[len - 1] - k, nums[i] + k);
            int least = Math.min(nums[0] + k, nums[i + 1] - k);
            res = Math.min(res, most - least);
        }
        return res;
    }
}
