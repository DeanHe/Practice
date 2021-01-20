package PrefixSum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
We define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.

If it exists, return true, otherwise return false.

Example
Example 1:

Input: [1,3,2,1,3,2,1,3,2,1,3]
Output: true
Explanation: (2,5,8) => Four subarrays are all [1, 3].
Example 2:

Input: [1,2,1,2,1,2,1]
Output: true
Explanation: (1,3,5)
Notice
1.1 <= n <= 2000.
2. Elements in the given array will be in range [-1,000,000, 1,000,000].
 */
public class SplitArrayWithEqualSum {
    /**
     * @param nums: a list of integer
     * @return: return a boolean
     */
    public boolean     splitArray(List<Integer> nums) {
        // write your code here
        int len = nums.size();
        int[] preSum = new int[len];
        preSum[0] = nums.get(0);
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + nums.get(i);
        }
        for (int j = 3; j + 3 < len; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (preSum[i - 1] == preSum[j - 1] - preSum[i]) {
                    set.add(preSum[i - 1]);
                }
            }
            for (int k = j + 2; k + 1 < len; k++) {
                if (preSum[k - 1] - preSum[j] == preSum[len - 1] - preSum[k] && set.contains(preSum[len - 1] - preSum[k])) {
                    return true;
                }
            }
        }
        return false;
    }
}
