"""
Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.

Example 1:
Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)

Example 2:
Input: nums = [1,2,4,5,10]
Output: 16
Explanation: There are 16 valid tuples:
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 10^4
All elements in nums are distinct.

hints:
1 Note that all of the integers are distinct. This means that each time a product is formed it must be formed by two unique integers.
2 Count the frequency of each product of 2 distinct numbers. Then calculate the permutations formed.

analysis:
math combination
TC: O(N^2)
"""
from collections import defaultdict
from typing import List


class TupleWithSameProduct:
    def tupleSameProduct(self, nums: List[int]) -> int:
        res = 0
        cnt = defaultdict(int)
        sz = len(nums)
        for i in range(sz - 1):
            for j in range(i + 1, sz):
                cnt[nums[i] * nums[j]] += 1
        for n in cnt.values():
            # Calculate the number of ways to choose two pairs with the same product
            res += n * (n - 1) // 2
        # each pair can form 8 tuples
        return res * 8
