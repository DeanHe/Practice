"""
You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

Example 1:
Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Example 2:
Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]

Constraints:
nums.length == k
1 <= k <= 3500
1 <= nums[i].length <= 50
-10^5 <= nums[i][j] <= 10^5
nums[i] is sorted in non-decreasing order.

analysis:
Let n be the total number of elements across all lists and k be the number of lists.
Time complexity: O(n * logk)
Space complexity: O(k)
"""
import heapq
from typing import List


class SmallestRangeCoveringElementsFromKLists:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        res = [float('-inf'), float('inf')]
        most = float('-inf')
        pq = []
        for i in range(len(nums)):
            heapq.heappush(pq, (nums[i][0], i, 0))
            most = max(most, nums[i][0])
        while pq:
            least, list_idx, i = heapq.heappop(pq)
            if most - least < res[1] - res[0]:
                res = [least, most]
            if i + 1 < len(nums[list_idx]):
                heapq.heappush(pq, (nums[list_idx][i + 1], list_idx, i + 1))
                most = max(most, nums[list_idx][i + 1])
            else:
                break
        return res