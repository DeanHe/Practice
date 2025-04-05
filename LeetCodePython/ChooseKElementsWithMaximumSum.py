"""
ou are given two integer arrays, nums1 and nums2, both of length n, along with a positive integer k.

For each index i from 0 to n - 1, perform the following:

Find all indices j where nums1[j] is less than nums1[i].
Choose at most k values of nums2[j] at these indices to maximize the total sum.
Return an array answer of size n, where answer[i] represents the result for the corresponding index i.

Example 1:
Input: nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2
Output: [80,30,0,80,50]
Explanation:
For i = 0: Select the 2 largest values from nums2 at indices [1, 2, 4] where nums1[j] < nums1[0], resulting in 50 + 30 = 80.
For i = 1: Select the 2 largest values from nums2 at index [2] where nums1[j] < nums1[1], resulting in 30.
For i = 2: No indices satisfy nums1[j] < nums1[2], resulting in 0.
For i = 3: Select the 2 largest values from nums2 at indices [0, 1, 2, 4] where nums1[j] < nums1[3], resulting in 50 + 30 = 80.
For i = 4: Select the 2 largest values from nums2 at indices [1, 2] where nums1[j] < nums1[4], resulting in 30 + 20 = 50.

Example 2:
Input: nums1 = [2,2,2,2], nums2 = [3,1,2,3], k = 1
Output: [0,0,0,0]
Explanation:
Since all elements in nums1 are equal, no indices satisfy the condition nums1[j] < nums1[i] for any i, resulting in 0 for all positions.

Constraints:
n == nums1.length == nums2.length
1 <= n <= 10^5
1 <= nums1[i], nums2[i] <= 10^6
1 <= k <= n

hints:
1 Sort nums1 and its corresponding nums2 values together based on nums1.
2 Use a max heap to track the top k values of nums2 as you process each element in the sorted order.
"""
import heapq
from typing import List


class ChooseKElementsWithMaximumSum:
    def findMaxSum(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:
        sz = len(nums1)
        res = [0] * sz
        nums1_idx = []
        for i in range(sz):
            nums1_idx.append((nums1[i], i))
        nums1_idx.sort()
        pq = []
        idx = nums1_idx[0][1]
        total = nums2[idx]
        heapq.heappush(pq, nums2[idx])
        for i in range(1, sz):
            idx = nums1_idx[i][1]
            val = nums1_idx[i][0]
            pre_idx = nums1_idx[i - 1][1]
            pre_val = nums1_idx[i - 1][0]
            if val == pre_val:
                res[idx] = res[pre_idx]
            else:
                res[idx] = total
            total += nums2[idx]
            heapq.heappush(pq, nums2[idx])
            if len(pq) > k:
                total -= heapq.heappop(pq)
        return res

