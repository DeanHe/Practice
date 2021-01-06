package BinarySearch;

/*
A split of an integer array is good if:

The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.



Example 1:

Input: nums = [1,1,1]
Output: 1
Explanation: The only good way to split nums is [1] [1] [1].
Example 2:

Input: nums = [1,2,2,2,5,0]
Output: 3
Explanation: There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
Example 3:

Input: nums = [3,2,1]
Output: 0
Explanation: There is no good way to split nums.


Constraints:

3 <= nums.length <= 105
0 <= nums[i] <= 104

analysis:
approach 1:
Use BinarySearch Template 1
Time Complexity: O(NlogN)
Space Complexity: O(N)

approach 2:
Final thing is to realize that j and k will only move forward, which result in a linear-time solution.
 */
public class WaysToSplitArrayIntoThreeSubarrays {
    int MOD = (int) (1e9 + 7);

    public int waysToSplit(int[] nums) {
        int len = nums.length;
        long res = 0;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for (int i = 0; i < len - 2; i++) {
            int leftSum = preSum[i];
            if (leftSum >= preSum[len - 1] - leftSum) {
                break;
            }
            int s = search(preSum, leftSum, i + 1, true);
            int e = search(preSum, leftSum, i + 1, false);
            if (s != -1 && e != -1) {
                res += e - s + 1;
            }
        }
        return (int) (res % MOD);
    }

    private int search(int[] preSum, int leftSum, int idx, boolean searchLeft) {
        int len = preSum.length, res = -1;
        int s = idx, e = len - 2; // mid subArray start is in range [s, e]
        while (s <= e) {
            int m = s + (e - s) / 2;
            int midSum = preSum[m] - leftSum;
            int rightSum = preSum[len - 1] - preSum[m];
            if (leftSum <= midSum && midSum <= rightSum) {
                res = m;
                if (searchLeft) {
                    e = m - 1;
                } else {
                    s = m + 1;
                }
            } else if (leftSum > midSum) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return res;
    }
}
