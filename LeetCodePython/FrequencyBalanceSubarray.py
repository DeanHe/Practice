"""
You are given an integer array nums.

Define a frequency balance subarray as follows:

If the subarray contains only one distinct value, it is frequency balanced.
Otherwise, there must exist a positive integer f such that every distinct value in the subarray occurs either f or 2 * f times, and both frequencies occur among the distinct values.
Return an integer denoting the length of the longest frequency balance subarray.

Example 1:
Input: nums = [1,2,2,1,2,3,3,3]
Output: 5

Explanation:
The longest frequency balance subarray is [2, 1, 2, 3, 3].
The elements that appear most frequently are 2 and 3, both appearing twice.
The remaining element 1 appears once, meeting the requirements.

Example 2:
Input: nums = [5,5,5,5]
Output: 4

Explanation:
The longest frequency balance subarray is [5, 5, 5, 5].
The element that appears most frequently is 5.
There are no other elements meeting the requirements.

Example 3:
Input: nums = [1,2,3,4]
Output: 1

Explanation:
Since all elements appear only once, the length of the longest frequency balance subarray is 1.

Constraints:
1 <= nums.length <= 10^3
1 <= nums[i] <= 10^9

hints:
1 For each candidate subarray, maintain the frequency of every distinct value.
2 A subarray with multiple distinct values is valid exactly when it has two distinct frequency values, and the larger one is twice the smaller one.
3 A subarray containing only one distinct value is always valid.
"""
from collections import defaultdict
from typing import List


class FrequencyBalanceSubarray:
    def getLength(self, nums: List[int]) -> int:
        res = 0
        sz = len(nums)
        for s in range(sz):
            cnt = defaultdict(int)
            freq_cnt = defaultdict(int)
            for e in range(s, sz):
                val = nums[e]
                if cnt[val] > 0:
                    # decrement old freq_cnt
                    freq_cnt[cnt[val]] -= 1
                    if freq_cnt[cnt[val]] == 0:
                        del freq_cnt[cnt[val]]
                cnt[val] += 1
                freq_cnt[cnt[val]] += 1
                if len(cnt) == 1 and len(freq_cnt) == 1:
                    res = max(res, e - s + 1)
                elif len(freq_cnt) == 2:
                    min_freq_cnt = min(freq_cnt.keys())
                    max_freq_cnt = max(freq_cnt.keys())
                    if 2 * min_freq_cnt == max_freq_cnt:
                        res = max(res, e - s + 1)
        return res
