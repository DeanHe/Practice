"""
You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.

Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.

Return the minimum maximum difference among all p pairs.

Example 1:
Input: nums = [10,1,2,7,1,3], p = 2
Output: 1
Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5.
The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:
Input: nums = [4,2,1,2], p = 1
Output: 0
Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.


Constraints:
1 <= nums.length <= 10^5
0 <= nums[i] <= 10^9
0 <= p <= (nums.length)/2

hints:
1 Can we use dynamic programming here?
2 To minimize the answer, the array should be sorted first.
3 The recurrence relation is fn(i, x) = min(fn(i+1, x), max(abs(nums[i]-nums[i+1]), fn(i+2, p-1)), and fn(0,p) gives the desired answer.

analysis:
binary search
TC: O(n * log(max(A)) + n * log * n)
"""

from typing import List


class MinimizeTheMaximumDifferenceOfPairs:
    def minimizeMax(self, nums: List[int], p: int) -> int:
        n = len(nums)
        nums.sort()
        s, e = 0, nums[-1] - nums[0]

        def canFindEnoughPairs(target):
            pairs = 0
            i = 1
            while i < n:
                if nums[i] - nums[i - 1] <= target:
                    pairs += 1
                    i += 1
                i += 1
            return pairs >= p

        while s + 1 < e:
            mid = (s + e) // 2
            if canFindEnoughPairs(mid):
                e = mid
            else:
                s = mid
        if canFindEnoughPairs(s):
            return s
        return e