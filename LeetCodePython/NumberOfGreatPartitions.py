"""
You are given an array nums consisting of positive integers and an integer k.

Partition the array into two ordered groups such that each element is in exactly one group. A partition is called great if the sum of elements of each group is greater than or equal to k.

Return the number of distinct great partitions. Since the answer may be too large, return it modulo 109 + 7.

Two partitions are considered distinct if some element nums[i] is in different groups in the two partitions.

Example 1:
Input: nums = [1,2,3,4], k = 4
Output: 6
Explanation: The great partitions are: ([1,2,3], [4]), ([1,3], [2,4]), ([1,4], [2,3]), ([2,3], [1,4]), ([2,4], [1,3]) and ([4], [1,2,3]).

Example 2:
Input: nums = [3,3,3], k = 4
Output: 0
Explanation: There are no great partitions for this array.

Example 3:
Input: nums = [6,6], k = 2
Output: 2
Explanation: We can either put nums[0] in the first partition or in the second partition.
The great partitions will be ([6], [6]) and ([6], [6]).

Constraints:
1 <= nums.length, k <= 1000
1 <= nums[i] <= 10^9

analysis:
Define a sub-problem:
Find the total number of subsets in the array with their sums smaller than k..
This is a classic DP problem SubsetSum with time complexity O(N * K).
For each of the invalid subset, it can be either the first or second of an invalid tuple.
So, let the number of invalid subsets be X, total number of subsets pairs is 2^n, total valid subsets should be 2^n - 2 * X.
Needs to think about edges cases where all pairs of subsets are invalid (2 * X > 2^n).

TC: O(N * K)
"""
from typing import List


class NumberOfGreatPartitions:
    def countPartitions(self, nums: List[int], k: int) -> int:
        mod = 10 ** 9 + 7
        n = len(nums)
        dp = [[-1] * (k + 1) for _ in range(n + 1)]
        for i in range(k + 1):
            dp[0][i] = 0
        dp[0][0] = 1

        def subset_sum_counts(idx, total):
            if total < 0:
                return 0
            if dp[idx][total] < 0:
                dp[idx][total] = subset_sum_counts(idx - 1, total) + subset_sum_counts(idx - 1, total - nums[idx - 1])
            return dp[idx][total]

        invalid_pairs = sum([subset_sum_counts(n, total) for total in range(k)]) * 2
        return max(2 ** n - invalid_pairs, 0) % mod