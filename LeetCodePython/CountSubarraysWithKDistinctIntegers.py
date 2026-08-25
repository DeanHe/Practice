"""
You are given an integer array nums and two integers k and m.

Return an integer denoting the count of subarrays of nums such that:

The subarray contains exactly k distinct integers.
Within the subarray, each distinct integer appears at least m times.


Example 1:
Input: nums = [1,2,1,2,2], k = 2, m = 2
Output: 2

Explanation:
The possible subarrays with k = 2 distinct integers, each appearing at least m = 2 times are:

Subarray	Distinct
numbers	Frequency
[1, 2, 1, 2]	{1, 2} → 2	{1: 2, 2: 2}
[1, 2, 1, 2, 2]	{1, 2} → 2	{1: 2, 2: 3}
Thus, the answer is 2.

Example 2:
Input: nums = [3,1,2,4], k = 2, m = 1
Output: 3
Explanation:
The possible subarrays with k = 2 distinct integers, each appearing at least m = 1 times are:

Subarray Distinct
numbers	Frequency
[3, 1]	{3, 1} → 2	{3: 1, 1: 1}
[1, 2]	{1, 2} → 2	{1: 1, 2: 1}
[2, 4]	{2, 4} → 2	{2: 1, 4: 1}
Thus, the answer is 3.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
1 <= k, m <= nums.length

hints:
1 Use sliding window.
2 Use the reduction: answer = at_most(k) - at_most(k-1). at_most(K) = number of subarrays with <= K distinct values where every present value appears >= m times.
"""
from collections import defaultdict


class CountSubarraysWithKDistinctIntegers:
    def countSubarrays(self, nums: list[int], k: int, m: int) -> int:
        res = 0
        distinct_count_window = defaultdict(int)
        distinct_count_window_l = 0
        frequent_window = defaultdict(int)
        frequent_window_l = 0
        frequency_ge_m_count = 0
        for num in nums:
            distinct_count_window[num] += 1
            while len(distinct_count_window) > k:
                distinct_count_window[nums[distinct_count_window_l]] -= 1
                if distinct_count_window[nums[distinct_count_window_l]] == 0:
                    del distinct_count_window[nums[distinct_count_window_l]]
                distinct_count_window_l += 1

            frequent_window[num] += 1
            if frequent_window[num] == m:
                frequency_ge_m_count += 1
            while frequency_ge_m_count >= k:
                if frequent_window[nums[frequent_window_l]] == m:
                    frequency_ge_m_count -= 1
                frequent_window[nums[frequent_window_l]] -= 1
                frequent_window_l += 1

            if distinct_count_window_l < frequent_window_l:
                res += frequent_window_l - distinct_count_window_l
        return res
