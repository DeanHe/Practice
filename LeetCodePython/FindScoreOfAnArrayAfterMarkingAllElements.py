"""
You are given an array nums consisting of positive integers.

Starting with score = 0, apply the following algorithm:

Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
Add the value of the chosen integer to score.
Mark the chosen element and its two adjacent elements if they exist.
Repeat until all the array elements are marked.
Return the score you get after applying the above algorithm.

Example 1:
Input: nums = [2,1,3,4,5,2]
Output: 7
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
Our score is 1 + 2 + 4 = 7.

Example 2:
Input: nums = [2,3,5,1,3,2]
Output: 5
Explanation: We mark the elements as follows:
- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
Our score is 1 + 2 + 2 = 5.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6

hints:
1 Try simulating the process of marking the elements and their adjacent.
2 If there is an element that was already marked, then you skip it.

analysis:
heap
TC: O(NlogN)
"""
import heapq
from typing import List


class Solution:
    def findScore(self, nums: List[int]) -> int:
        res = 0
        sz = len(nums)
        marked = [False] * sz
        pq = [(n, i) for i, n in enumerate(nums)]
        heapq.heapify(pq)
        while pq:
            n, i = heapq.heappop(pq)
            if not marked[i]:
                res += n
                marked[i] = True
                if i - 1 >= 0:
                    marked[i - 1] = True
                if i + 1 < sz:
                    marked[i + 1] = True
        return res
