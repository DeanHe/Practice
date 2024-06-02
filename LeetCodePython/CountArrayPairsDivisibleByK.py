"""
Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) such that:

0 <= i < j <= n - 1 and
nums[i] * nums[j] is divisible by k.

Example 1:
Input: nums = [1,2,3,4,5], k = 2
Output: 7
Explanation:
The 7 pairs of indices whose corresponding products are divisible by 2 are
(0, 1), (0, 3), (1, 2), (1, 3), (1, 4), (2, 3), and (3, 4).
Their products are 2, 4, 6, 8, 10, 12, and 20 respectively.
Other pairs such as (0, 2) and (2, 4) have products 3 and 15 respectively, which are not divisible by 2.

Example 2:
Input: nums = [1,2,3,4], k = 5
Output: 0
Explanation: There does not exist any pair of indices whose corresponding product is divisible by 5.


Constraints:
1 <= nums.length <= 10^5
1 <= nums[i], k <= 10^5

hints:
1 For any element in the array, what is the smallest number it should be multiplied with such that the product is divisible by k?
2 The smallest number which should be multiplied with nums[i] so that the product is divisible by k is k / gcd(k, nums[i]). Now think about how you can store and update the count of such numbers present in the array efficiently.
"""
from collections import defaultdict
from typing import List


class CountArrayPairsDivisibleByK:
    def countPairs(self, nums: List[int], k: int) -> int:
        res = 0
        cnt = defaultdict(int)

        def gcd(a, b):
            if a == 0:
                return b
            return gcd(b % a, a)

        for num in nums:
            common_with_k = gcd(num, k)
            for n in cnt.keys():
                if (common_with_k * n) % k == 0:
                    res += cnt[n]
            cnt[common_with_k] += 1
        return res


