"""
You are given an integer array nums and an integer k.

You are allowed to perform the following operation on each element of the array at most once:

Add an integer in the range [-k, k] to the element.
Return the maximum possible number of distinct elements in nums after performing the operations.

Example 1:
Input: nums = [1,2,2,3,3,4], k = 2
Output: 6
Explanation:
nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.

Example 2:
Input: nums = [4,4,4,4], k = 1
Output: 3
Explanation:
By adding -1 to nums[0] and 1 to nums[1], nums changes to [3, 5, 4, 4].

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= k <= 10^9

hints:
1 Can we use sorting here?
2 Find the minimum element which is not used for each element.

analysis:
Greedy:
TC: O(N)
SC: O(N)
"""
from typing import List


class MaximumNumberOfDistinctElementsAfterOperations:
    def maxDistinctElements(self, nums: List[int], k: int) -> int:
        sz = len(nums)
        nums.sort()
        visited = set()
        visited.add(nums[0])
        most = nums[0]
        for i in range(1, sz):
            if nums[i] in visited:
                if most + 1 <= nums[i] + 2 * k:
                    most += 1
                    visited.add(most)
            else:
                most = max(most, nums[i])
                visited.add(most)
        return len(visited)


