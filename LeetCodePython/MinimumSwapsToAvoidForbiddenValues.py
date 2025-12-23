"""
You are given two integer arrays, nums and forbidden, each of length n.

You may perform the following operation any number of times (including zero):

Choose two distinct indices i and j, and swap nums[i] with nums[j].
Return the minimum number of swaps required such that, for every index i, the value of nums[i] is not equal to forbidden[i]. If no amount of swaps can ensure that every index avoids its forbidden value, return -1.

Example 1:
Input: nums = [1,2,3], forbidden = [3,2,1]
Output: 1

Explanation:
One optimal set of swaps:
Select indices i = 0 and j = 1 in nums and swap them, resulting in nums = [2, 1, 3].
After this swap, for every index i, nums[i] is not equal to forbidden[i].

Example 2:
Input: nums = [4,6,6,5], forbidden = [4,6,5,5]
Output: 2

Explanation:
One optimal set of swaps:
Select indices i = 0 and j = 2 in nums and swap them, resulting in nums = [6, 6, 4, 5].
Select indices i = 1 and j = 3 in nums and swap them, resulting in nums = [6, 5, 4, 6].
After these swaps, for every index i, nums[i] is not equal to forbidden[i].

Example 3:
Input: nums = [7,7], forbidden = [8,7]

Output: -1
Explanation:
It is not possible to make nums[i] different from forbidden[i] for all indices.

Example 4:
Input: nums = [1,2], forbidden = [2,1]
Output: 0

Explanation:
No swaps are required because nums[i] is already different from forbidden[i] for all indices, so the answer is 0.

Constraints:
1 <= n == nums.length == forbidden.length <= 10^5
1 <= nums[i], forbidden[i] <= 10^9

hints:
1 Solve the problem greedily.
2 Count combined frequencies of values in nums and forbidden into a map freq.
3 If any freq[val] >= n + 1 return -1 (impossible).
4 Collect bad positions (nums[i] == forbidden[i]) into a map badPairs[val] counts.
5 Let badPairsSum be the sum of all bad counts and maxBadPairs the maximum bad count for any single value.
6 The minimum swaps equals max((badPairsSum + 1) / 2, maxBadPairs) (i.e. ceil(badPairsSum/2) vs the largest same-value bad cluster).

analysis:
Each swap fixing at most two bad positions.
Dominant values that limit pairing options.

TC:O(N)
"""
from collections import defaultdict
from typing import List


class MinimumSwapsToAvoidForbiddenValues:
    def minSwaps(self, nums: List[int], forbidden: List[int]) -> int:
        sz = len(nums)
        num_cnt = defaultdict(int)
        forbid_cnt = defaultdict(int)
        for num in nums:
            num_cnt[num] += 1
        for num in forbidden:
            forbid_cnt[num] += 1
        for num in num_cnt.keys():
            if num_cnt[num] + forbid_cnt[num] > sz:
                return -1
        bad_pair_cnt = 0
        bad_num_cnt = defaultdict(int)
        for i in range(sz):
            if nums[i] == forbidden[i]:
                bad_pair_cnt += 1
                num = nums[i]
                bad_num_cnt[num] += 1
        if bad_pair_cnt == 0:
            return 0
        max_freq = max(bad_num_cnt.values())
        return max((bad_pair_cnt + 1) // 2, max_freq)


