"""
You are given an integer array nums and two integers a and b.

For a subarray, let:

x be the number of even elements.
y be the number of odd elements.
The ratio of even to odd elements in a subarray is defined as x / y, where ratios are compared by their exact rational values.

A subarray is considered valid if:

y > 0, and
x / y <= a / b.
Return the number of valid subarrays in nums.

Example 1:
Input: nums = [1,2,1,2], a = 3, b = 2
Output: 7

Explanation:
The following are the valid subarrays:

Subarray	Values	Even Count	Odd Count	Ratio
nums[0..0]	[1]	0	1	0 / 1
nums[0..1]	[1, 2]	1	1	1 / 1
nums[0..2]	[1, 2, 1]	1	2	1 / 2
nums[0..3]	[1, 2, 1, 2]	2	2	2 / 2
nums[1..2]	[2, 1]	1	1	1 / 1
nums[2..2]	[1]	0	1	0 / 1
nums[2..3]	[1, 2]	1	1	1 / 1
Thus, the number of valid subarrays is 7.

Example 2:
Input: nums = [2,2,1], a = 2, b = 1
Output: 3

Explanation:
The following are the valid subarrays:
Subarray	Values	Even Count	Odd Count	Ratio
nums[0..2]	[2, 2, 1]	2	1	2 / 1
nums[1..2]	[2, 1]	1	1	1 / 1
nums[2..2]	[1]	0	1	0 / 1
Thus, the number of valid subarrays is 3.

Example 3:
Input: nums = [2,2,2], a = 1, b = 1
Output: 0

Explanation:
Every subarray contains 0 odd numbers, so no subarray is valid.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= a, b <= 10^9

hints:
1 Replace every even element with b and every odd element with -a. A subarray is valid exactly when its transformed sum is at most 0.
2 The condition y > 0 is then automatic, because a non-empty subarray containing only even elements has a positive transformed sum.
3 Let pref[i] be the prefix sum of the transformed array. A subarray [l, r] is valid when pref[r + 1] <= pref[l].
4 Scan the prefix sums from left to right and count how many previous prefix sums are greater than or equal to the current one using coordinate compression and a Fenwick tree.
"""
from sortedcontainers import SortedList


class CountSubarraysWithEvenOddRatioII:
    def countRatioSubarrays(self, nums: list[int], a: int, b: int) -> int:
        ls = SortedList([0])
        res = pre_sum = 0
        for num in nums:
            pre_sum += a if num % 2 else -b
            res += ls.bisect_right(pre_sum)
            ls.add(pre_sum)
        return res