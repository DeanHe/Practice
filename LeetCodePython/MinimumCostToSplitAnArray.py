"""
You are given an integer array nums and an integer k.

Split the array into some number of non-empty subarrays. The cost of a split is the sum of the importance value of each subarray in the split.

Let trimmed(subarray) be the version of the subarray where all numbers which appear only once are removed.

For example, trimmed([3,1,2,4,3,4]) = [3,4,3,4].
The importance value of a subarray is k + trimmed(subarray).length.

For example, if a subarray is [1,2,3,3,3,4,4], then trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4].The importance value of this subarray will be k + 5.
Return the minimum possible cost of a split of nums.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,2,1,2,1,3,3], k = 2
Output: 8
Explanation: We split nums to have two subarrays: [1,2], [1,2,1,3,3].
The importance value of [1,2] is 2 + (0) = 2.
The importance value of [1,2,1,3,3] is 2 + (2 + 2) = 6.
The cost of the split is 2 + 6 = 8. It can be shown that this is the minimum possible cost among all the possible splits.
Example 2:

Input: nums = [1,2,1,2,1], k = 2
Output: 6
Explanation: We split nums to have two subarrays: [1,2], [1,2,1].
The importance value of [1,2] is 2 + (0) = 2.
The importance value of [1,2,1] is 2 + (2) = 4.
The cost of the split is 2 + 4 = 6. It can be shown that this is the minimum possible cost among all the possible splits.
Example 3:

Input: nums = [1,2,1,2,1], k = 5
Output: 10
Explanation: We split nums to have one subarray: [1,2,1,2,1].
The importance value of [1,2,1,2,1] is 5 + (3 + 2) = 10.
The cost of the split is 10. It can be shown that this is the minimum possible cost among all the possible splits.

Constraints:
1 <= nums.length <= 1000
0 <= nums[i] < nums.length
1 <= k <= 10^9

hints:
1 Let's denote dp[r] = minimum cost to partition the first r elements of nums. What would be the transitions of such dynamic programming?
2 dp[r] = min(dp[l] + importance(nums[l..r])) over all 0 <= l < r. This already gives us an O(n^3) approach, as importance can be calculated in linear time, and there are a total of O(n^2) transitions.
3 Can you think of a way to compute multiple importance values of related subarrays faster?
4 importance(nums[l-1..r]) is either importance(nums[l..r]) if a new unique element is added, importance(nums[l..r]) + 1 if an old element that appeared at least twice is added, or importance(nums[l..r]) + 2, if a previously unique element is duplicated. This allows us to compute importance(nums[l..r]) for all 0 <= l < r in O(n) by keeping a frequency table and decreasing l from r-1 down to 0.

analysis:
dfs(s) means min cost to partition nums[s:]
TC: O(N^2) + O(N^2)
"""
from collections import defaultdict
from functools import lru_cache, cache
from typing import List


class MinimumCostToSplitAnArray:
    def minCost(self, nums: List[int], k: int) -> int:
        n = len(nums)

        @cache
        def dfs(start):
            if start == n:
                return 0
            cost = float('inf')
            cnt = defaultdict(int)
            trimmed_len = 0
            for end in range(start, n):
                if cnt[nums[end]] == 1:
                    trimmed_len += 2
                elif cnt[nums[end]] > 1:
                    trimmed_len += 1
                cnt[nums[end]] += 1
                cut = k + trimmed_len
                cost = min(cost, cut + dfs(end + 1))
            return cost

        return dfs(0)
