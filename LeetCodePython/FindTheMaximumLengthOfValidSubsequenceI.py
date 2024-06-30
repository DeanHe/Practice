"""
You are given an integer array nums.
A
subsequence
 sub of nums with length x is called valid if it satisfies:

(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
Return the length of the longest valid subsequence of nums.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: nums = [1,2,3,4]
Output: 4
Explanation:
The longest valid subsequence is [1, 2, 3, 4].

Example 2:
Input: nums = [1,2,1,1,2,1,2]
Output: 6
Explanation:
The longest valid subsequence is [1, 2, 1, 2, 1, 2].

Example 3:
Input: nums = [1,3]
Output: 2
Explanation:
The longest valid subsequence is [1, 3].

Constraints:
2 <= nums.length <= 2 * 10^5
1 <= nums[i] <= 10^7

hints:
1 The possible sequence either contains all even elements, all odd elements, alternate even odd, or alternate odd even elements.
2 Considering only the parity of elements, there are only 4 possibilities and we can try all of them.
3 When selecting an element with any parity, try to select the earliest one.

Analysis:
TC: O(N)
SC: O(1)
"""
from typing import List


class FindTheMaximumLengthOfValidSubsequenceI:
    def maximumLength(self, nums: List[int]) -> int:
        odd_cnt = even_cnt = rotate_cnt = 0
        mod = nums[0] % 2
        for num in nums:
            if num % 2 == 0:
                even_cnt += 1
            else:
                odd_cnt += 1
            if num % 2 == mod:
                rotate_cnt += 1
                mod = 1 - mod
        return max(even_cnt, odd_cnt, rotate_cnt)
