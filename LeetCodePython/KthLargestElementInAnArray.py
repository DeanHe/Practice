"""
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

analysis:
quick select
 based on selection algorithm (based on the partition method - the same one as used in quicksort).
O(N) best case / O(N^2) worst case running time + O(1) memory
"""
from typing import List


class KthLargestElementInAnArray:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def getKth(s, e, k):
            if s == e:
                return nums[s]

            def partition():
                pivot = nums[e]
                i = s
                for j in range(s, e):
                    if nums[j] < pivot:
                        nums[i], nums[j] = nums[j], nums[i]
                        i += 1
                nums[i], nums[e] = nums[e], nums[i]
                return i

            idx = partition()
            if idx == k:
                return nums[idx]
            elif idx < k:
                return getKth(idx + 1, e, k)
            else:
                return getKth(s, idx - 1, k)

        return getKth(0, len(nums) - 1, len(nums) - k)
