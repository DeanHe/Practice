"""
You are given an integer array nums, and an integer k.

Start with an initial value val = 1 and process nums from left to right. At each index i, you must choose exactly one of the following actions:

Multiply val by nums[i].
Divide val by nums[i].
Leave val unchanged.
After processing all elements, val is considered equal to k only if its final rational value exactly equals k.

Return the count of distinct sequences of choices that result in val == k.

Note: Division is rational (exact), not integer division. For example, 2 / 4 = 1 / 2.

Example 1:
Input: nums = [2,3,2], k = 6
Output: 2

Explanation:
The following 2 distinct sequences of choices result in val == k:
Sequence	Operation on nums[0]	Operation on nums[1]	Operation on nums[2]	Final val
1	Multiply: val = 1 * 2 = 2	Multiply: val = 2 * 3 = 6	Leave val unchanged	6
2	Leave val unchanged	Multiply: val = 1 * 3 = 3	Multiply: val = 3 * 2 = 6	6

Example 2:
Input: nums = [4,6,3], k = 2
Output: 2

Explanation:
The following 2 distinct sequences of choices result in val == k:
Sequence	Operation on nums[0]	Operation on nums[1]	Operation on nums[2]	Final val
1	Multiply: val = 1 * 4 = 4	Divide: val = 4 / 6 = 2 / 3	Multiply: val = (2 / 3) * 3 = 2	2
2	Leave val unchanged	Multiply: val = 1 * 6 = 6	Divide: val = 6 / 3 = 2	2

Example 3:
Input: nums = [1,5], k = 1
Output: 3

Explanation:
The following 3 distinct sequences of choices result in val == k:
Sequence	Operation on nums[0]	Operation on nums[1]	Final val
1	Multiply: val = 1 * 1 = 1	Leave val unchanged	1
2	Divide: val = 1 / 1 = 1	Leave val unchanged	1
3	Leave val unchanged	Leave val unchanged	1


Constraints:
1 <= nums.length <= 19
1 <= nums[i] <= 6
1 <= k <= 10^15

hints:
1 Represent numbers by their prime exponents as (2^x, 3^y, 5^z).
2 Use Dynamic Programming on the prime exponents: let dp(idx, x, y, z) be the number of ways to reach exponents (x,y,z) after processing index idx.
3 When idx == nums.length, compare (x, y, z) to the prime exponent decomposition of k and count matches.

analysis:
since 1 <= nums[i] <= 6, it must be as (2^x, 3^y, 5^z)
"""
from functools import cache
from typing import List


class CountSequencesToK:
    def countSequences(self, nums: List[int], k: int) -> int:

        def decompose(x):
            twos = threes = fives = 0
            while x != 1:
                while x % 2 == 0:
                    x //= 2
                    twos += 1
                while x % 3 == 0:
                    x //= 3
                    threes += 1
                while x % 5 == 0:
                    x //= 5
                    fives += 1
            return twos, threes, fives

        nums_decomposed = [decompose(num) for num in nums]

        @cache
        def dfs(i, twos, threes, fives):
            if i == len(nums):
                if (2 ** twos) * (3 ** threes) * (5 ** fives) == k:
                    return 1
                return 0
            res = 0
            # skip
            res += dfs(i + 1, twos, threes, fives)
            num_twos, num_threes, num_fives = nums_decomposed[i]
            # multiply
            res += dfs(i + 1, twos + num_twos, threes + num_threes, fives + num_fives)
            # divide
            res += dfs(i + 1, twos - num_twos, threes - num_threes, fives - num_fives)
            return res

        return dfs(0, 0, 0, 0)