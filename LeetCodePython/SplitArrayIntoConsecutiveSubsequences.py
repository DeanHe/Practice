"""
You are given an integer array nums that is sorted in non-decreasing order.

Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:

Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
All subsequences have a length of 3 or more.
Return true if you can split nums according to the above conditions, or false otherwise.

A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).

Example 1:
Input: nums = [1,2,3,3,4,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,5] --> 1, 2, 3
[1,2,3,3,4,5] --> 3, 4, 5

Example 2:
Input: nums = [1,2,3,3,4,4,5,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
[1,2,3,3,4,4,5,5] --> 3, 4, 5

Example 3:
Input: nums = [1,2,3,4,4,5]
Output: false
Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.

Constraints:
1 <= nums.length <= 104
-1000 <= nums[i] <= 1000
nums is sorted in non-decreasing order.
"""
import collections
from typing import List


class SplitArrayIntoConsecutiveSubsequences:
    def isPossible(self, nums: List[int]) -> bool:
        cnt = collections.Counter(nums)
        nxt = collections.defaultdict(int)
        for n in nums:
            if cnt[n] == 0:
                continue
            elif nxt[n] > 0:
                nxt[n] -= 1
                nxt[n + 1] += 1
            elif n + 1 in cnt and cnt[n + 1] > 0 and n + 2 in cnt and cnt[n + 2] > 0:
                cnt[n + 1] -= 1
                cnt[n + 2] -= 1
                nxt[n + 3] += 1
            else:
                return False
            cnt[n] -= 1
        return True
