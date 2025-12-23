"""
You are given a 0-indexed array nums of length n, consisting of non-negative integers. For each index i from 0 to n - 1, you must determine the size of the minimum sized non-empty subarray of nums starting at i (inclusive) that has the maximum possible bitwise OR.

In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find the smallest subarray starting at i, such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
The bitwise OR of an array is the bitwise OR of all the numbers in it.

Return an integer array answer of size n where answer[i] is the length of the minimum sized subarray starting at i with maximum bitwise OR.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,0,2,1,3]
Output: [3,3,2,2,1]
Explanation:
The maximum possible bitwise OR starting at any index is 3.
- Starting at index 0, the shortest subarray that yields it is [1,0,2].
- Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
- Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
- Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
- Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
Therefore, we return [3,3,2,2,1].

Example 2:
Input: nums = [1,2]
Output: [2,1]
Explanation:
Starting at index 0, the shortest subarray that yields the maximum bitwise OR is of length 2.
Starting at index 1, the shortest subarray that yields the maximum bitwise OR is of length 1.
Therefore, we return [2,1].

Constraints:
n == nums.length
1 <= n <= 10^5
0 <= nums[i] <= 10^9

hints:
1 Consider trying to solve the problem for each bit position separately.
2 For each bit position, find the position of the next number that has a 1 in that position, if any.
3 Take the maximum distance to such a number, including the current number.
4 Iterate backwards to achieve a linear complexity.

analysis:
pos[bit] means the smallest index of nums in binary form has bit position as 1
Bitwise, window
TC: O(N)
"""
from typing import List


class SmallestSubarraysWithMaximumBitwiseOR:
    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        res = [0] * len(nums)
        pos = [-1] * 31
        for l in range(len(nums) - 1, -1, -1):
            r = l
            for b in range(31):
                if (nums[l] & (1 << b)) > 0:
                    pos[b] = l
                else:
                    if pos[b] != -1:
                        r = max(r, pos[b])
            res[l] = r - l + 1
        return res

