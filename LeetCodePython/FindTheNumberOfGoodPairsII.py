"""
You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively. You are also given a positive integer k.

A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).

Return the total number of good pairs.

Example 1:
Input: nums1 = [1,3,4], nums2 = [1,3,4], k = 1
Output: 5
Explanation:
The 5 good pairs are (0, 0), (1, 0), (1, 1), (2, 0), and (2, 2).

Example 2:
Input: nums1 = [1,2,4,12], nums2 = [2,4], k = 3
Output: 2
Explanation:
The 2 good pairs are (3, 0) and (3, 1).

Constraints:
1 <= n, m <= 10^5
1 <= nums1[i], nums2[j] <= 10^6
1 <= k <= 10^3

hints:
1 Let f[v] be the number of occurrences of v/k in nums2.
2 For each value v in nums1, enumerating all its factors d (in sqrt(v) time) and sum up all the f[d] to get the final answer.
3 It is also possible to improve the complexity from v * sqrt(v) to v * log(v) - How?

analysis:
TC: O(len(nums1) * len(sqrt(n)))
"""
from collections import defaultdict
from math import sqrt
from typing import List


class FindTheNumberOfGoodPairsII:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], k: int) -> int:
        res = 0
        cnt2 = defaultdict(int)
        for n in nums2:
            cnt2[n * k] += 1
        for n in nums1:
            square_root = int(sqrt(n))
            for i in range(1, square_root + 1):
                if n % i == 0:
                    res += cnt2[i]
                    if i != n // i:
                        res += cnt2[n // i]
        return res

    # multiply num2 factor to match nums1, using space to trade off time cost
    def numberOfPairs2(self, nums1: List[int], nums2: List[int], k: int) -> int:
        res = 0
        num1_max = max(nums1)
        cnt2 = defaultdict(int)
        multiple_factor_cnt = [0] * (num1_max + 1)
        for n in nums2:
            factor = n * k
            if factor <= num1_max:
                cnt2[factor] += 1
        for factor, freq in cnt2.items():
            for i in range(factor, num1_max + 1, factor):
                multiple_factor_cnt[i] += freq
        for n in nums1:
            res += multiple_factor_cnt[n]
        return res

