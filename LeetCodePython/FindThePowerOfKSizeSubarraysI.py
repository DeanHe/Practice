"""
You are given an array of integers nums of length n and a positive integer k.

The power of an array is defined as:

Its maximum element if all of its elements are consecutive and sorted in ascending order.
-1 otherwise.
You need to find the power of all
subarrays
 of nums of size k.

Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

Example 1:
Input: nums = [1,2,3,4,3,2,5], k = 3
Output: [3,4,-1,-1,-1]

Explanation:
There are 5 subarrays of nums of size 3:
[1, 2, 3] with the maximum element 3.
[2, 3, 4] with the maximum element 4.
[3, 4, 3] whose elements are not consecutive.
[4, 3, 2] whose elements are not sorted.
[3, 2, 5] whose elements are not consecutive.

Example 2:
Input: nums = [2,2,2,2,2], k = 4
Output: [-1,-1]

Example 3:
Input: nums = [3,2,3,2,3,2], k = 2
Output: [-1,3,-1,3,-1]

Constraints:
1 <= n == nums.length <= 500
1 <= nums[i] <= 10^5
1 <= k <= n

Analysis:
TC: O(N)
SC: O(1)
"""
from collections import deque
from typing import List


class FindThePowerOfKSizeSubarraysI:
    def resultsArray(self, nums: List[int], k: int) -> List[int]:
        if k == 1:
            return nums
        res = []
        consecutive_cnt = 1
        for i in range(1, len(nums)):
            if nums[i - 1] + 1 == nums[i]:
                consecutive_cnt += 1
            else:
                consecutive_cnt = 1
            if k - 1 <= i:
                if consecutive_cnt >= k:
                    res.append(nums[i])
                else:
                    res.append(-1)
        return res

    def resultsArray2(self, nums: List[int], k: int) -> List[int]:
        res = []
        q = deque()
        for i, n in enumerate(nums):
            if q and q[-1] + 1 != n:
                q.clear()
            if len(q) == k:
                q.popleft()
            q.append(n)
            if k - 1 <= i:
                if len(q) == k:
                    res.append(n)
                else:
                    res.append(-1)
        return res


