"""
You are given an integer array nums and a positive integer k.
A subsequence sub of nums with length x is called valid if it satisfies:

(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
Return the length of the longest valid subsequence of nums.

Example 1:
Input: nums = [1,2,3,4,5], k = 2
Output: 5
Explanation:
The longest valid subsequence is [1, 2, 3, 4, 5].

Example 2:
Input: nums = [1,4,2,3,1,4], k = 3

Output: 4
Explanation:
The longest valid subsequence is [1, 4, 1, 4].

Constraints:
2 <= nums.length <= 10^3
1 <= nums[i] <= 10^7
1 <= k <= 10^3

hints:
1 Fix the value of (subs[0] + subs[1]) % k from the k possible values. Let it be val.
2 Let dp[i] store the maximum length of a subsequence with its last element x such that x % k == i.
3 Answer for a subsequence ending at index y is dp[(k + val - (y % k)) % k] + 1.

analysis:
DP
TC: O(N^2)
"""
from collections import defaultdict
from typing import List


class FindTheMaximumLengthOfValidSubsequenceII:
    def maximumLength(self, nums: List[int], k: int) -> int:
        sz = len(nums)
        dp = defaultdict(int)
        for i in range(sz):
            for j in range(i + 1, sz):
                val = (nums[i] + nums[j]) % k
                if (j, val) not in dp:
                    dp[j, val] = 2
                dp[j, val] = max(dp[j, val], dp[i, val] + 1)
        return max(dp.values())

